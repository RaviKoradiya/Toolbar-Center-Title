# Toolbar-Center-Title

Toolbar-Center-Title is build to align title center within existing ActionBar.

[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)[![](https://jitpack.io/v/RaviKoradiya/Toolbar-Center-Title.svg)](https://jitpack.io/#RaviKoradiya/Toolbar-Center-Title)

## Dependency

Add this in your project level `build.gradle` file (**not** your module `build.gradle` file):

```gradle
allprojects {
	repositories {
        maven { url "https://jitpack.io" } // add this line
    }
}
```

Then, add the library to your module `build.gradle`
```gradle
dependencies {
    compile 'com.github.RaviKoradiya:Toolbar-Center-Title:1.0.3'
}
```

## Usage

With Data Binding...

```xml
<android.support.v7.widget.Toolbar
	android:id="@+id/toolbar"
	android:layout_width="match_parent"
	android:layout_height="?attr/actionBarSize"
	android:background="?attr/colorPrimary"
	app:popupTheme="@style/AppTheme.PopupOverlay"
	bind:centerTitle='@{true}' />
```
Or

```java
CenterTitle.centerTitle(toolbar,true);
```
That's it!

Java and Kotlin Support...


License
--------

    Copyright 2018 Ravi Koradiya

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
       http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.