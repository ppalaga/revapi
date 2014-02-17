/*
 * Copyright 2014 Lukas Krejci
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package org.revapi;

import java.util.Iterator;

/**
 * Represents a set of archives that define an API.
 *
 * @author Lukas Krejci
 * @since 0.1
 */
public final class API {
    private final Iterable<? extends Archive> archives;
    private final Iterable<? extends Archive> supplementaryArchives;

    /**
     * @see #getArchives()
     * @see #getSupplementaryArchives()
     */
    public API(Iterable<? extends Archive> archives,
        Iterable<? extends Archive> supplementaryArchives) {
        this.archives = archives;
        this.supplementaryArchives = supplementaryArchives;
    }

    /**
     * The set of archives to check the API of.
     */
    public Iterable<? extends Archive> getArchives() {
        return archives;
    }

    /**
     * The set of archives that somehow supplement the main ones (for example they contain
     * definitions used in the main archives). In Java, supplementary archives would be
     * the JARs that need to be on the compilation classpath. Can be null if no such
     * archives are needed.
     */
    public Iterable<? extends Archive> getSupplementaryArchives() {
        return supplementaryArchives;
    }

    @Override
    public String toString() {
        StringBuilder bld = new StringBuilder("API[archives=");
        addArchivesToString(bld, archives);
        bld.append(", supplementary=");
        addArchivesToString(bld, supplementaryArchives);
        bld.append("]");
        return bld.toString();
    }

    private static void addArchivesToString(StringBuilder bld, Iterable<? extends Archive> archives) {
        bld.append("[");

        if (archives != null) {
            Iterator<? extends Archive> it = archives.iterator();
            if (it.hasNext()) {
                bld.append(it.next().getName());
            }

            while (it.hasNext()) {
                bld.append(", ").append(it.next().getName());
            }
        }

        bld.append("]");
    }
}