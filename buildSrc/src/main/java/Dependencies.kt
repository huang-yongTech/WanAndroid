object Versions {
    //android build
    const val androidBuildToolVersion = "30.0.3"
    const val androidCompileSdkVersion = 31
    const val androidTargetSdkVersion = 30
    const val androidMinSdkVersion = 21
    const val androidVersionCode = 1
    const val androidVersionName = "1.0"
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    //kotlin
    const val ktxCoreVersion = "1.2.0"

    //android libraries
    const val appcompatVersion = "1.1.0"
    const val materialVersion = "1.1.0"
    const val supportV4Version = "1.0.0"
    const val collectionVersion = "1.1.0"
    const val constraintLayoutVersion = "1.1.3"
    const val navigationVersion = "2.2.2"
    const val lifecycleVersion = "2.2.0"
    const val flexBoxVersion = "2.0.1"
    const val roomVersion = "2.3.0"
    const val pagingVersion = "2.1.2"

    //third libraries
    const val rxJavaVersion = "2.2.2"
    const val rxAndroidVersion = "2.1.0"
    const val retrofitVersion = "2.4.0"
    const val adapterRxjavaVersion = "2.4.0"
    const val converterGsonVersion = "2.4.0"
    const val okHttpVersion = "3.10.0"
    const val rxbindingVersion = "2.1.1"
    const val rxPermissionVersion = "0.10.2"

    const val baseRecyclerViewAdapterHelperVersion = "3.0.1"
    const val smartRefreshlayoutVersion = "1.1.0"

    const val glideVersion = "4.10.0"
    const val glideTransformationVersion = "4.1.0"
    const val aRouterVersion = "1.4.1"
    const val aRouterCompilerVersion = "1.2.2"

    const val bannerVersion = "2.0.0-alpha03"

    const val webrtcVersion = "1.0.28513"
    const val socketIOversion = "1.0.0"

    //内存泄漏检测
    const val leakcanaryVersion = "2.3"

    //Testing
    const val junitVersion = "4.12"
}

object Deps {
    //android build
    const val androidBuildToolVersion = Versions.androidBuildToolVersion
    const val androidCompileSdkVersion = Versions.androidCompileSdkVersion
    const val androidTargetSdkVersion = Versions.androidTargetSdkVersion
    const val androidMinSdkVersion = Versions.androidMinSdkVersion
    const val androidVersionCode = Versions.androidVersionCode
    const val androidVersionName = Versions.androidVersionName
    const val testInstrumentationRunner = Versions.testInstrumentationRunner

    //android libraries
    const val appcompatVersion = "androidx.appcompat:appcompat:${Versions.appcompatVersion}"
    const val materialVersion = "com.google.android.material:material:${Versions.materialVersion}"
    const val supportV4Version = "androidx.legacy:legacy-support-v4:${Versions.supportV4Version}"

    const val navigationFragmentVersion = "androidx.navigation:navigation-fragment:${Versions.navigationVersion}"
    const val navigationUiVersion = "androidx.navigation:navigation-ui:${Versions.navigationVersion}"

    const val lifecycleViewModelVersion = "androidx.lifecycle:lifecycle-viewmodel:${Versions.lifecycleVersion}"
    const val lifecycleLiveDataVersion = "androidx.lifecycle:lifecycle-livedata:${Versions.lifecycleVersion}"
    const val lifecycleRuntimeVersion = "androidx.lifecycle:lifecycle-runtime:${Versions.lifecycleVersion}"
    const val lifecycleViewModelSaveStateVersion = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycleVersion}"
    const val lifecycleCommonJava8Version = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycleVersion}"
    const val constraintLayoutVersion = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    const val pagingVersion = "androidx.paging:paging-runtime:${Versions.pagingVersion}"
    const val roomVersion = "androidx.room:room-runtime:${Versions.roomVersion}"
    const val roomCompilerVersion = "androidx.room:room-compiler:${Versions.roomVersion}"
    const val roomKtxVersion = "androidx.room:room-ktx:${Versions.roomVersion}"
    const val flexBoxVersion = "com.google.android:flexbox:${Versions.flexBoxVersion}"

    const val ktxCoreVersion = "androidx.core:core-ktx:${Versions.ktxCoreVersion}"

    //third libraries
    const val glideVersion = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
    const val glideCompilerVersion = "com.github.bumptech.glide:compiler:${Versions.glideVersion}"
    const val glideTransformationVersion = "jp.wasabeef:glide-transformations:${Versions.glideTransformationVersion}"

    const val rxPermissionVersion = "com.github.tbruyelle:rxpermissions:${Versions.rxPermissionVersion}"
    const val rxJavaVersion = "io.reactivex.rxjava2:rxjava:${Versions.rxJavaVersion}"
    const val rxAndroidVersion = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroidVersion}"

    const val okHttpVersion = "com.squareup.okhttp3:okhttp:${Versions.okHttpVersion}"
    const val okHttpLogVersion = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpVersion}"
    const val retrofitVersion = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val adapterRxjavaVersion = "com.squareup.retrofit2:adapter-rxjava2:${Versions.adapterRxjavaVersion}"
    const val converterGsonVersion = "com.squareup.retrofit2:converter-gson:${Versions.converterGsonVersion}"

    const val smartRefreshlayoutVersion = "com.scwang.smartrefresh:SmartRefreshLayout:${Versions.smartRefreshlayoutVersion}"
    const val baseRecyclerViewAdapterHelperVersion = "com.github.CymChad:BaseRecyclerViewAdapterHelper:${Versions.baseRecyclerViewAdapterHelperVersion}"
    const val bannerVersion = "com.youth.banner:banner:${Versions.bannerVersion}"

    //内存泄漏检测
    const val leakcanaryVersion = "com.squareup.leakcanary:leakcanary-android:${Versions.leakcanaryVersion}"

    //webrtc
    const val webrtcVersion = "org.webrtc:google-webrtc:${Versions.webrtcVersion}"
    const val socketIOversion = "io.socket:socket.io-client:${Versions.socketIOversion}"
}