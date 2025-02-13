/*******************************************************************************
 * Copyright (c) 2021,2022 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package io.openliberty.concurrent.internal.processor;

import java.lang.reflect.Member;
import java.util.Collections;
import java.util.List;

import org.osgi.service.component.annotations.Component;

import com.ibm.websphere.ras.Tr;
import com.ibm.websphere.ras.TraceComponent;
import com.ibm.websphere.ras.annotation.Trivial;
import com.ibm.ws.javaee.dd.common.JNDIEnvironmentRef;
import com.ibm.ws.javaee.dd.common.ManagedExecutor;
import com.ibm.wsspi.injectionengine.InjectionBinding;
import com.ibm.wsspi.injectionengine.InjectionException;
import com.ibm.wsspi.injectionengine.InjectionProcessor;
import com.ibm.wsspi.injectionengine.InjectionProcessorProvider;

import jakarta.enterprise.concurrent.ManagedExecutorDefinition;

/**
 * Registers a provider to process ManagedExecutorDefinition.
 */
@Component(service = InjectionProcessorProvider.class)
public class ManagedExecutorDefinitionProvider extends InjectionProcessorProvider<ManagedExecutorDefinition, ManagedExecutorDefinition.List> {

    private static final TraceComponent tc = Tr.register(ManagedExecutorDefinitionProvider.class);

    private static final List<Class<? extends JNDIEnvironmentRef>> REF_CLASSES = //
                    Collections.<Class<? extends JNDIEnvironmentRef>> singletonList(ManagedExecutor.class);

    @Override
    @Trivial
    public Class<ManagedExecutorDefinition> getAnnotationClass() {
        return ManagedExecutorDefinition.class;
    }

    @Override
    @Trivial
    public Class<ManagedExecutorDefinition.List> getAnnotationsClass() {
        return ManagedExecutorDefinition.List.class;
    }

    @Override
    @Trivial
    public List<Class<? extends JNDIEnvironmentRef>> getJNDIEnvironmentRefClasses() {
        return REF_CLASSES;
    }

    @Override
    public InjectionProcessor<ManagedExecutorDefinition, ManagedExecutorDefinition.List> createInjectionProcessor() {
        return new Processor();
    }

    class Processor extends InjectionProcessor<ManagedExecutorDefinition, ManagedExecutorDefinition.List> {
        @Trivial
        public Processor() {
            super(ManagedExecutorDefinition.class, ManagedExecutorDefinition.List.class);
        }

        @Override
        public InjectionBinding<ManagedExecutorDefinition> createInjectionBinding(ManagedExecutorDefinition annotation,
                                                                                  Class<?> instanceClass, Member member,
                                                                                  String jndiName) throws InjectionException {
            final boolean trace = TraceComponent.isAnyTracingEnabled() && tc.isEntryEnabled();
            if (trace)
                Tr.entry(this, tc, "createInjectionBinding", ManagedExecutorDefinitionBinding.toString(annotation), instanceClass, member, jndiName);

            InjectionBinding<ManagedExecutorDefinition> injectionBinding = //
                            new ManagedExecutorDefinitionBinding(jndiName, ivNameSpaceConfig);
            injectionBinding.merge(annotation, instanceClass, null);

            if (trace)
                Tr.exit(this, tc, "createInjectionBinding", injectionBinding);
            return injectionBinding;
        }

        @Override
        @Trivial
        public ManagedExecutorDefinition[] getAnnotations(ManagedExecutorDefinition.List pluralAnnotation) {
            ManagedExecutorDefinition[] annos = pluralAnnotation.value();

            if (TraceComponent.isAnyTracingEnabled() && tc.isDebugEnabled()) {
                Object[] a = new String[annos.length];
                for (int i = 0; i < annos.length; i++)
                    a[i] = new StringBuilder().append("ManagedExecutorDefinition@").append(Integer.toHexString(annos[i].hashCode())) //
                                    .append(' ').append(annos[i].name()) //
                                    .toString();
                Tr.debug(this, tc, "getAnnotations", a);
            }
            return annos;
        }

        @Override
        @Trivial
        public String getJndiName(ManagedExecutorDefinition annotation) {
            return annotation.name();
        }

        @Override
        public void processXML() throws InjectionException {
            List<? extends ManagedExecutor> managedExecutorDefinitions = //
                            ivNameSpaceConfig.getJNDIEnvironmentRefs(ManagedExecutor.class);

            if (managedExecutorDefinitions != null)
                for (ManagedExecutor managedExecutor : managedExecutorDefinitions) {
                    String jndiName = managedExecutor.getName();
                    InjectionBinding<ManagedExecutorDefinition> injectionBinding = ivAllAnnotationsCollection.get(jndiName);

                    ManagedExecutorDefinitionBinding binding;
                    if (injectionBinding == null) {
                        binding = new ManagedExecutorDefinitionBinding(jndiName, ivNameSpaceConfig);
                        addInjectionBinding(binding);
                    } else {
                        binding = (ManagedExecutorDefinitionBinding) injectionBinding;
                    }

                    binding.mergeXML(managedExecutor);
                }
        }

        @Override
        public void resolve(InjectionBinding<ManagedExecutorDefinition> injectionBinding) throws InjectionException {
            ((ManagedExecutorDefinitionBinding) injectionBinding).resolve();
        }
    }
}