/*
 * jlib - Open Source Java Library
 *
 *     www.jlib.org
 *
 *
 *     Copyright 2005-2015 Igor Akkerman
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package org.jlib.message;

import org.jlib.value.formatter.MessageFormatNamedValueFormatter;
import org.jlib.value.formatter.PrintfNamedValueFormatter;

import static org.jlib.message.MessageAssert.assertThat;
import static org.jlib.message.Messages.message;
import static org.jlib.value.Values.named;
import org.junit.Before;
import org.junit.Test;

public class EagerMessageTest {

    private static final MessageStyle COLON_PRINTF_CONFIG;

    static {
        COLON_PRINTF_CONFIG = new MessageStyle();
        COLON_PRINTF_CONFIG.setArgumentFormatter(new PrintfNamedValueFormatter("%s: %s"));
        COLON_PRINTF_CONFIG.setBetweenTextAndArguments(" ");
        COLON_PRINTF_CONFIG.setBeforeArguments("(");
        COLON_PRINTF_CONFIG.setBetweenArguments("; ");
        COLON_PRINTF_CONFIG.setAfterArguments(")");
    }

    private static final MessageStyle EQUALS_QUOTE_PRINTF_CONFIG;

    static {
        EQUALS_QUOTE_PRINTF_CONFIG = new MessageStyle();
        EQUALS_QUOTE_PRINTF_CONFIG.setArgumentFormatter(new PrintfNamedValueFormatter("%s='%s'"));
        EQUALS_QUOTE_PRINTF_CONFIG.setBetweenTextAndArguments(" ");
        EQUALS_QUOTE_PRINTF_CONFIG.setBeforeArguments("[");
        EQUALS_QUOTE_PRINTF_CONFIG.setBetweenArguments(" ");
        EQUALS_QUOTE_PRINTF_CONFIG.setAfterArguments("]");
    }

    private static final MessageStyle COLON_MF_CONFIG;

    static {
        COLON_MF_CONFIG = new MessageStyle();
        COLON_MF_CONFIG.setArgumentFormatter(new MessageFormatNamedValueFormatter("{0}: {1}"));
        COLON_MF_CONFIG.setBetweenTextAndArguments(" ");
        COLON_MF_CONFIG.setBetweenArguments("; ");
    }

    @Before
    public void initializeDefaultMessageStyle() {
        DefaultMessageSetup.getInstance().setDefaultMessageStyle(EQUALS_QUOTE_PRINTF_CONFIG);
    }

    @Test
    public void messageWithNoTextAndEmptyArgumentsList() {
        final Message message = message().with();

        assertThat(message).isEmpty();
    }

    @Test
    public void messageWithNoTextAndSingleArgument() {
        final Message message = message().with("dummyName", "Dummy Value");

        assertThat(message).isEqualTo("[dummyName='Dummy Value']");
    }

    @Test
    public void messageWithNoTextAndMultipleArguments() {
        final Message message = message().with("dummyName", 1).with("dummerName", "Dummer Value");

        assertThat(message).isEqualTo("[dummyName='1' dummerName='Dummer Value']");
    }

    @Test
    public void messageWithTextAndNoArguments() {
        final Message message = message("Something went wrong.");

        assertThat(message).isEqualTo("Something went wrong.");
    }

    @Test
    public void messageWithTextAndEmptyArgumentsList() {
        final Message message = message("Something went wrong.").with();

        assertThat(message).isEqualTo("Something went wrong.");
    }

    @Test
    public void messageWithTextAndArguments() {
        final Message message = /*
         */ message("Something went wrong.").with("dummyName", 1).with("dummerName", "Dummer Value");

        assertThat(message).isEqualTo("Something went wrong. [dummyName='1' dummerName='Dummer Value']");
    }

    @Test
    public void messageWithTextAndNamedArgument() {
        final Message message = message("Something went wrong.").with(named("dummyName", 1));

        assertThat(message).isEqualTo("Something went wrong. [dummyName='1']");
    }

    @Test
    public void messageWithTextAndNamedArgumentsInSpecifiedDefaultFormat() {

        DefaultMessageSetup.getInstance().setDefaultMessageStyle(COLON_PRINTF_CONFIG);

        final Message message = /*
         */ message("Something went wrong.").with(named("dummyName", 1),
                                                  named("dummerName", "Dummer Value"));

        assertThat(message).isEqualTo("Something went wrong. (dummyName: 1; dummerName: Dummer Value)");
    }

    @Test
    public void messageWithTextAndNamedArguments() {
        final Message message = /*
         */ message("Something went wrong.").with(named("dummyName", 1),
                                                  named("dummerName", "Dummer Value"));

        assertThat(message).isEqualTo("Something went wrong. [dummyName='1' dummerName='Dummer Value']");
    }

    @Test
    public void messageWithTextArgumentsInSpecifiedPrintfFormat() {
        final Message message = /*
         */ message("Something went wrong.", COLON_PRINTF_CONFIG).with("dummyName", 1)
                                                                 .with("dummerName", "Dummer Value");

        assertThat(message).isEqualTo("Something went wrong. (dummyName: 1; dummerName: Dummer Value)");
    }

    @Test
    public void messageWithTextAndArgumentsInSpecifiedMfFormat() {
        final Message message = /*
          */ message("Something went wrong.", COLON_MF_CONFIG).with("dummyName", 1)
                                                              .with("dummerName", "Dummer Value");

        assertThat(message).isEqualTo("Something went wrong. dummyName: 1; dummerName: Dummer Value");
    }
}
