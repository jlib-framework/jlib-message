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

import java.util.Formatter;

import java.text.MessageFormat;

import lombok.experimental.UtilityClass;
import org.jlib.value.formatter.PrintfNamedValueFormatter;

@UtilityClass
public final class Messages {

    public static final MessageFactory INITIAL_DEFAULT_MESSAGE_FACTORY = EagerMessage::new;

    public static final int EXPECTED_ARGUMENTS_COUNT = 5;
    public static final int EXPECTED_ARGUMENT_LENGTH = 64;

    public static Message message() {
        return getFactory().newMessage();
    }

    public static Message message(final Object object) {
        return getFactory().newMessage(object.toString());
    }

    public static Message message(final Object object, final MessageStyle messageStyle) {
        return getFactory().newMessage(object.toString(), messageStyle);
    }

    public static Message mfmessage(final String messageTemplate, final Object... messageArguments) {
        return getFactory().newMessage(MessageFormat.format(messageTemplate, messageArguments));
    }

    public static Message pfmessage(final String messageTemplate, final Object... messageArguments) {
        final StringBuilder messageBuilder = createBuilder(messageTemplate.length(), messageArguments.length);
        new Formatter(messageBuilder).format(messageTemplate, messageArguments);
        return getFactory().newMessage(messageBuilder);
    }

    public static MessageStyle createInitialDefaultMessageStyle() {
        final MessageStyle defaultMessageStyle = new MessageStyle();

        defaultMessageStyle.setArgumentFormatter(new PrintfNamedValueFormatter("%s: <%s>"));
        defaultMessageStyle.setBetweenTextAndArguments(" ");
        defaultMessageStyle.setBetweenArguments(" ");

        return defaultMessageStyle;
    }

    public static MessageFactory getFactory() {
        return DefaultMessageSetup.INSTANCE.getDefaultMessageFactory();
    }

    public static StringBuilder createBuilder(final int textLength, final int argumentsCount) {
        return new StringBuilder(textLength + argumentsCount * EXPECTED_ARGUMENT_LENGTH);
    }
}
