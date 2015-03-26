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

import java.io.Serializable;

import org.jlib.value.formatter.NamedValueFormatter;

public class MessageStyle
implements Serializable {

    private static final long serialVersionUID = 4551938257105373490L;

    private NamedValueFormatter<Object> argumentFormatter;
    private String betweenTextAndArguments = "";
    private String beforeArguments = "";
    private String betweenArguments = "";
    private String afterArguments = "";

    public NamedValueFormatter<Object> getArgumentFormatter() {
        return argumentFormatter;
    }

    public void setArgumentFormatter(final NamedValueFormatter<Object> argumentFormatter) {
        this.argumentFormatter = argumentFormatter;
    }

    public void setBetweenTextAndArguments(final String betweenTextAndArguments) {
        this.betweenTextAndArguments = betweenTextAndArguments;
    }

    public String getBetweenTextAndArguments() {
        return betweenTextAndArguments;
    }

    public void setBetweenArguments(final String betweenArguments) {
        this.betweenArguments = betweenArguments;
    }

    public String getBeforeArguments() {
        return beforeArguments;
    }

    public void setBeforeArguments(final String beforeArguments) {
        this.beforeArguments = beforeArguments;
    }

    public String getBetweenArguments() {
        return betweenArguments;
    }

    public String getAfterArguments() {
        return afterArguments;
    }

    public void setAfterArguments(final String afterArguments) {
        this.afterArguments = afterArguments;
    }
}
