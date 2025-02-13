/*******************************************************************************
 * Copyright (c) 2011 IBM Corporation and others.
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
package com.ibm.ws.security.utility.utils;

import java.io.Console;
import java.io.IOError;
import java.io.PrintStream;

/**
 * Wraps the standard input console, and allows us to handle char[] to
 * String conversion and exception logic. This also allows us to mock
 * up the console for unit test purposes.
 */
public class ConsoleWrapper {
    private final Console console;
    private final PrintStream stderr;

    public ConsoleWrapper(Console console, PrintStream stderr) {
        this.console = console;
        this.stderr = stderr;
    }

    /**
     * Determines if the Console is currently available.
     * 
     * @return true if the Console is available, false otherwise.
     */
    public boolean isInputStreamAvailable() {
        return console != null;
    }

    /**
     * Reads text from the input String, prompting with the given String.
     * The values entered on the console are masked (not echoed back to
     * the console).
     * 
     * @param prompt
     * @return String read from input.
     */
    public String readMaskedText(String prompt) {
        if (!isInputStreamAvailable()) {
            stderr.println(CommandUtils.getMessage("error.inputConsoleNotAvailable"));
            return null;
        }
        try {
            char[] in = console.readPassword(prompt);
            if (in == null) {
                return null;
            } else {
                return String.valueOf(in);
            }
        } catch (IOError e) {
            stderr.println("Exception while reading stdin: " + e.getMessage());
            e.printStackTrace(stderr);
        }
        return null;
    }
}
