/*
 * Copyright (c) 2009-2012, bad robot (london) ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package bad.robot.http.apache;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;

public class ApacheHttpAuthenticationCredentials {

    private final AuthScope scope;
    private final UsernamePasswordCredentials user;

    public ApacheHttpAuthenticationCredentials(AuthScope scope, UsernamePasswordCredentials user) {
        this.scope = scope;
        this.user = user;
    }

    public AuthScope getScope() {
        return scope;
    }

    public UsernamePasswordCredentials getUser() {
        return user;
    }
}
