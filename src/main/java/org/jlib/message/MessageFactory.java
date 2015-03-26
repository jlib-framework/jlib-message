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

@FunctionalInterface
public interface MessageFactory {

    default Message newMessage() {
        return newMessage("");
    }

    default Message newMessage(final String text) {
        return newMessage(text, DefaultMessageSetup.getInstance().getDefaultMessageStyle());
    }

    default Message newMessage(final String text, final MessageStyle messageStyle) {
        return newMessage(MessageUtility.createBuilder(text.length(), MessageUtility.EXPECTED_ARGUMENTS_COUNT).append(text), messageStyle);
    }

    default Message newMessage(final StringBuilder builder) {
        return newMessage(builder, DefaultMessageSetup.getInstance().getDefaultMessageStyle());
    }

    Message newMessage(StringBuilder builder, MessageStyle style);
}
