## [PureMVC](http://puremvc.github.com/) Kotlin MultiCore Framework

PureMVC is a lightweight framework for creating applications based upon the classic [Model-View-Controller](http://en.wikipedia.org/wiki/Model-view-controller) design meta-pattern. It supports [modular programming](http://en.wikipedia.org/wiki/Modular_programming) through the use of [Multiton](http://en.wikipedia.org/wiki/Multiton) Core actors instead of the [Singletons](http://en.wikipedia.org/wiki/Singleton_pattern).

* [API Docs](http://puremvc.org/pages/docs/Java/multicore)
* [Unit Tests](http://puremvc.github.io/pages/images/screenshots/PureMVC-Shot-Kotlin-Multicore-UnitTests.png)

## Gradle Installation
**Step 1.** Add the JitPack repository to your build file. 
Add it in your root build.gradle at the end of repositories:
```groovy
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
**Step 2.** Add the dependency
```groovy
dependencies {
  implementation 'com.github.PureMVC:puremvc-kotlin-multicore-framework:1.1.0'
}
```
## Demos
* [Employee Admin Android App](https://github.com/PureMVC/purmevc-kotlin-demo-android-employeeadmin/wiki)

## Platforms / Technologies
* [Kotlin](https://en.wikipedia.org/wiki/Kotlin_(programming_language))
* [Android](https://en.wikipedia.org/wiki/Android_(operating_system))

## Status
Production - [Version 1.1](https://github.com/PureMVC/puremvc-java-multicore-framework/blob/master/VERSION)

## License
* PureMVC MultiCore Framework for Kotlin - Copyright © 2020 [Saad Shams](https://www.linkedin.com/in/muizz)
* PureMVC - Copyright © 2020 [Futurescale, Inc.](http://futurescale.com/)
* All rights reserved.

* Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

  * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
  * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
  * Neither the name of Futurescale, Inc., PureMVC.org, nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
