/*
 * Copyright The OpenTelemetry Authors
 * SPDX-License-Identifier: Apache-2.0
 */

/*******************************************************************************
 * Copyright (c) 2026 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package io.openliberty.microprofile.metrics50.mcp.attributes;

// DO NOT EDIT, this is an Auto-generated file from
// buildscripts/templates/registry/incubating_java/IncubatingSemanticAttributes.java.j2
@SuppressWarnings("unused")
public final class RpcIncubatingAttributes {
    /**
     * Deprecated, use {@code rpc.response.status_code} attribute instead.
     *
     * @deprecated Replaced by {@code rpc.response.status_code}.
     */
    @Deprecated
    public static final String RPC_CONNECT_RPC_ERROR_CODE = "rpc.connect_rpc.error_code";

    /**
     * Deprecated, use {@code rpc.request.metadata} instead.
     *
     * @deprecated Replaced by {@code rpc.request.metadata}.
     */
    @Deprecated
    public static final String RPC_CONNECT_RPC_REQUEST_METADATA = "rpc.connect_rpc.request.metadata";

    /**
     * Deprecated, use {@code rpc.response.metadata} instead.
     *
     * @deprecated Replaced by {@code rpc.response.metadata}.
     */
    @Deprecated
    public static final String RPC_CONNECT_RPC_RESPONSE_METADATA = "rpc.connect_rpc.response.metadata";

    /**
     * Deprecated, use {@code rpc.request.metadata} instead.
     *
     * @deprecated Replaced by {@code rpc.request.metadata}.
     */
    @Deprecated
    public static final String RPC_GRPC_REQUEST_METADATA = "rpc.grpc.request.metadata";

    /**
     * Deprecated, use {@code rpc.response.metadata} instead.
     *
     * @deprecated Replaced by {@code rpc.response.metadata}.
     */
    @Deprecated
    public static final String RPC_GRPC_RESPONSE_METADATA = "rpc.grpc.response.metadata";

    /**
     * Deprecated, use string representation on the {@code rpc.response.status_code}
     * attribute instead.
     *
     * @deprecated Use string representation of the gRPC status code on the {@code
     *     rpc.response.status_code} attribute.
     */
    @Deprecated
    public static final String RPC_GRPC_STATUS_CODE = "rpc.grpc.status_code";

    /**
     * Deprecated, use string representation on the {@code rpc.response.status_code}
     * attribute instead.
     *
     * @deprecated Use string representation of the error code on the
     *             {@code rpc.response.status_code} attribute.
     */
    @Deprecated
    public static final String RPC_JSONRPC_ERROR_CODE = "rpc.jsonrpc.error_code";

    /**
     * Deprecated, use the span status description when reporting JSON-RPC spans.
     *
     * @deprecated Use the span status description when reporting JSON-RPC spans.
     */
    @Deprecated
    public static final String RPC_JSONRPC_ERROR_MESSAGE = "rpc.jsonrpc.error_message";

    /**
     * Deprecated, use {@code jsonrpc.request.id} instead.
     *
     * @deprecated Replaced by {@code jsonrpc.request.id}.
     */
    @Deprecated
    public static final String RPC_JSONRPC_REQUEST_ID = "rpc.jsonrpc.request_id";

    /**
     * Deprecated, use {@code jsonrpc.protocol.version} instead.
     *
     * @deprecated Replaced by {@code jsonrpc.protocol.version}.
     */
    @Deprecated
    public static final String RPC_JSONRPC_VERSION = "rpc.jsonrpc.version";

    /**
     * Compressed size of the message in bytes.
     *
     * @deprecated Deprecated, no replacement at this time.
     */
    @Deprecated
    public static final String RPC_MESSAGE_COMPRESSED_SIZE = "rpc.message.compressed_size";

    /**
     * MUST be calculated as two different counters starting from {@code 1} one for
     * sent messages and one for received message.
     *
     * <p>
     * Notes:
     *
     * <p>
     * This way we guarantee that the values will be consistent between different
     * implementations.
     *
     * @deprecated Deprecated, no replacement at this time.
     */
    @Deprecated
    public static final String RPC_MESSAGE_ID = "rpc.message.id";

    /**
     * Whether this is a received or sent message.
     *
     * @deprecated Deprecated, no replacement at this time.
     */
    @Deprecated
    public static final String RPC_MESSAGE_TYPE = "rpc.message.type";

    /**
     * Uncompressed size of the message in bytes.
     *
     * @deprecated Deprecated, no replacement at this time.
     */
    @Deprecated
    public static final String RPC_MESSAGE_UNCOMPRESSED_SIZE = "rpc.message.uncompressed_size";

    /**
     * The fully-qualified logical name of the method from the RPC interface
     * perspective.
     *
     * <p>
     * Notes:
     *
     * <p>
     * The method name MAY have unbounded cardinality in edge or error cases.
     *
     * <p>
     * Some RPC frameworks or libraries provide a fixed set of recognized methods
     * for client stubs and server implementations. Instrumentations for such
     * frameworks MUST set this attribute to the original method name only when the
     * method is recognized by the framework or library.
     *
     * <p>
     * When the method is not recognized, for example, when the server receives a
     * request for a method that is not predefined on the server, or when
     * instrumentation is not able to reliably detect if the method is predefined,
     * the attribute MUST be set to {@code _OTHER}. In such cases, tracing
     * instrumentations MUST also set {@code rpc.method_original} attribute to the
     * original method value.
     *
     * <p>
     * If the RPC instrumentation could end up converting valid RPC methods to
     * {@code _OTHER}, then it SHOULD provide a way to configure the list of
     * recognized RPC methods.
     *
     * <p>
     * The {@code rpc.method} can be different from the name of any implementing
     * method/function. The {@code code.function.name} attribute may be used to
     * record the fully-qualified method actually executing the call on the server
     * side, or the RPC client stub method on the client side.
     */
    public static final String RPC_METHOD = "rpc.method";

    /** The original name of the method used by the client. */
    public static final String RPC_METHOD_ORIGINAL = "rpc.method_original";

    /**
     * RPC request metadata, {@code <key>} being the normalized RPC metadata key
     * (lowercase), the value being the metadata values.
     *
     * <p>
     * Notes:
     *
     * <p>
     * Instrumentations SHOULD require an explicit configuration of which metadata
     * values are to be captured. Including all request metadata values can be a
     * security risk - explicit configuration helps avoid leaking sensitive
     * information.
     *
     * <p>
     * For example, a property {@code my-custom-key} with value
     * {@code ["1.2.3.4", "1.2.3.5"]} SHOULD be recorded as
     * {@code rpc.request.metadata.my-custom-key} attribute with value {@code
     * ["1.2.3.4", "1.2.3.5"]}
     */
    public static final String RPC_REQUEST_METADATA = "rpc.request.metadata";

    /**
     * RPC response metadata, {@code <key>} being the normalized RPC metadata key
     * (lowercase), the value being the metadata values.
     *
     * <p>
     * Notes:
     *
     * <p>
     * Instrumentations SHOULD require an explicit configuration of which metadata
     * values are to be captured. Including all response metadata values can be a
     * security risk - explicit configuration helps avoid leaking sensitive
     * information.
     *
     * <p>
     * For example, a property {@code my-custom-key} with value
     * {@code ["attribute_value"]} SHOULD be recorded as the
     * {@code rpc.response.metadata.my-custom-key} attribute with value {@code
     * ["attribute_value"]}
     */
    public static final String RPC_RESPONSE_METADATA = "rpc.response.metadata";

    /**
     * Status code of the RPC returned by the RPC server or generated by the client
     *
     * <p>
     * Notes:
     *
     * <p>
     * Usually it represents an error code, but may also represent partial success,
     * warning, or differentiate between various types of successful outcomes.
     * Semantic conventions for individual RPC frameworks SHOULD document what
     * {@code rpc.response.status_code} means in the context of that system and
     * which values are considered to represent errors.
     */
    public static final String RPC_RESPONSE_STATUS_CODE = "rpc.response.status_code";

    /**
     * Deprecated, use fully-qualified {@code rpc.method} instead.
     *
     * @deprecated Value should be included in {@code rpc.method} which is expected
     *             to be a fully-qualified name.
     */
    @Deprecated
    public static final String RPC_SERVICE = "rpc.service";

    /**
     * Deprecated, use {@code rpc.system.name} attribute instead.
     *
     * @deprecated Replaced by {@code rpc.system.name}.
     */
    @Deprecated
    public static final String RPC_SYSTEM = "rpc.system";

    /**
     * The Remote Procedure Call (RPC) system.
     *
     * <p>
     * Notes:
     *
     * <p>
     * The client and server RPC systems may differ for the same RPC interaction.
     * For example, a client may use Apache Dubbo or Connect RPC to communicate with
     * a server that uses gRPC since both protocols provide compatibility with gRPC.
     */
    public static final String RPC_SYSTEM_NAME = "rpc.system.name";

    // Enum definitions

    /**
     * Values for {@link #RPC_CONNECT_RPC_ERROR_CODE}
     *
     * @deprecated Replaced by {@code rpc.response.status_code}.
     */
    @Deprecated
    public static final class RpcConnectRpcErrorCodeIncubatingValues {
        /** cancelled. */
        @Deprecated
        public static final String CANCELLED = "cancelled";

        /** unknown. */
        @Deprecated
        public static final String UNKNOWN = "unknown";

        /** invalid_argument. */
        @Deprecated
        public static final String INVALID_ARGUMENT = "invalid_argument";

        /** deadline_exceeded. */
        @Deprecated
        public static final String DEADLINE_EXCEEDED = "deadline_exceeded";

        /** not_found. */
        @Deprecated
        public static final String NOT_FOUND = "not_found";

        /** already_exists. */
        @Deprecated
        public static final String ALREADY_EXISTS = "already_exists";

        /** permission_denied. */
        @Deprecated
        public static final String PERMISSION_DENIED = "permission_denied";

        /** resource_exhausted. */
        @Deprecated
        public static final String RESOURCE_EXHAUSTED = "resource_exhausted";

        /** failed_precondition. */
        @Deprecated
        public static final String FAILED_PRECONDITION = "failed_precondition";

        /** aborted. */
        @Deprecated
        public static final String ABORTED = "aborted";

        /** out_of_range. */
        @Deprecated
        public static final String OUT_OF_RANGE = "out_of_range";

        /** unimplemented. */
        @Deprecated
        public static final String UNIMPLEMENTED = "unimplemented";

        /** internal. */
        @Deprecated
        public static final String INTERNAL = "internal";

        /** unavailable. */
        @Deprecated
        public static final String UNAVAILABLE = "unavailable";

        /** data_loss. */
        @Deprecated
        public static final String DATA_LOSS = "data_loss";

        /** unauthenticated. */
        @Deprecated
        public static final String UNAUTHENTICATED = "unauthenticated";

        private RpcConnectRpcErrorCodeIncubatingValues() {
        }
    }

    /**
     * Values for {@link #RPC_GRPC_STATUS_CODE}
     *
     * @deprecated Use string representation of the gRPC status code on the {@code
     *     rpc.response.status_code} attribute.
     */
    @Deprecated
    public static final class RpcGrpcStatusCodeIncubatingValues {
        /** OK */
        @Deprecated
        public static final long OK = 0;

        /** CANCELLED */
        @Deprecated
        public static final long CANCELLED = 1;

        /** UNKNOWN */
        @Deprecated
        public static final long UNKNOWN = 2;

        /** INVALID_ARGUMENT */
        @Deprecated
        public static final long INVALID_ARGUMENT = 3;

        /** DEADLINE_EXCEEDED */
        @Deprecated
        public static final long DEADLINE_EXCEEDED = 4;

        /** NOT_FOUND */
        @Deprecated
        public static final long NOT_FOUND = 5;

        /** ALREADY_EXISTS */
        @Deprecated
        public static final long ALREADY_EXISTS = 6;

        /** PERMISSION_DENIED */
        @Deprecated
        public static final long PERMISSION_DENIED = 7;

        /** RESOURCE_EXHAUSTED */
        @Deprecated
        public static final long RESOURCE_EXHAUSTED = 8;

        /** FAILED_PRECONDITION */
        @Deprecated
        public static final long FAILED_PRECONDITION = 9;

        /** ABORTED */
        @Deprecated
        public static final long ABORTED = 10;

        /** OUT_OF_RANGE */
        @Deprecated
        public static final long OUT_OF_RANGE = 11;

        /** UNIMPLEMENTED */
        @Deprecated
        public static final long UNIMPLEMENTED = 12;

        /** INTERNAL */
        @Deprecated
        public static final long INTERNAL = 13;

        /** UNAVAILABLE */
        @Deprecated
        public static final long UNAVAILABLE = 14;

        /** DATA_LOSS */
        @Deprecated
        public static final long DATA_LOSS = 15;

        /** UNAUTHENTICATED */
        @Deprecated
        public static final long UNAUTHENTICATED = 16;

        private RpcGrpcStatusCodeIncubatingValues() {
        }
    }

    /**
     * Values for {@link #RPC_MESSAGE_TYPE}
     *
     * @deprecated Deprecated, no replacement at this time.
     */
    @Deprecated
    public static final class RpcMessageTypeIncubatingValues {
        /** sent. */
        @Deprecated
        public static final String SENT = "SENT";

        /** received. */
        @Deprecated
        public static final String RECEIVED = "RECEIVED";

        private RpcMessageTypeIncubatingValues() {
        }
    }

    /**
     * Values for {@link #RPC_SYSTEM}
     *
     * @deprecated Replaced by {@code rpc.system.name}.
     */
    @Deprecated
    public static final class RpcSystemIncubatingValues {
        /** gRPC */
        @Deprecated
        public static final String GRPC = "grpc";

        /** Java RMI */
        @Deprecated
        public static final String JAVA_RMI = "java_rmi";

        /** .NET WCF */
        @Deprecated
        public static final String DOTNET_WCF = "dotnet_wcf";

        /** Apache Dubbo */
        @Deprecated
        public static final String APACHE_DUBBO = "apache_dubbo";

        /** Connect RPC */
        @Deprecated
        public static final String CONNECT_RPC = "connect_rpc";

        /**
         * <a href="https://datatracker.ietf.org/doc/html/rfc5531">ONC RPC (Sun RPC)</a>
         */
        @Deprecated
        public static final String ONC_RPC = "onc_rpc";

        /** JSON-RPC */
        @Deprecated
        public static final String JSONRPC = "jsonrpc";

        private RpcSystemIncubatingValues() {
        }
    }

    /** Values for {@link #RPC_SYSTEM_NAME}. */
    public static final class RpcSystemNameIncubatingValues {
        /** <a href="https://grpc.io/">gRPC</a> */
        public static final String GRPC = "grpc";

        /** <a href="https://dubbo.apache.org/">Apache Dubbo</a> */
        public static final String DUBBO = "dubbo";

        /** <a href="https://connectrpc.com/">Connect RPC</a> */
        public static final String CONNECTRPC = "connectrpc";

        /** <a href="https://www.jsonrpc.org/">JSON-RPC</a> */
        public static final String JSONRPC = "jsonrpc";

        private RpcSystemNameIncubatingValues() {
        }
    }

    private RpcIncubatingAttributes() {
    }
}
