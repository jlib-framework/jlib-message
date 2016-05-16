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

import static lombok.AccessLevel.PRIVATE;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public final class DefaultMessageSetup {

    public static final DefaultMessageSetup INSTANCE = new DefaultMessageSetup();

    private MessageFactory defaultMessageFactory = Messages.INITIAL_DEFAULT_MESSAGE_FACTORY;
    private MessageStyle defaultMessageStyle = Messages.createInitialDefaultMessageStyle();

    public MessageFactory getDefaultMessageFactory() {
        return defaultMessageFactory;
    }

    public void setDefaultMessageFactory(final MessageFactory defaultMessageFactory) {
        this.defaultMessageFactory = defaultMessageFactory;
    }

    public MessageStyle getDefaultMessageStyle() {
        return defaultMessageStyle;
    }

    public void setDefaultMessageStyle(final MessageStyle defaultMessageStyle) {
        this.defaultMessageStyle = defaultMessageStyle;
    }
}
