//
// Created by David Luna on 29/06/24_
//
#include <jni.h>
#define UNUSED(x) (void)(x)

JNIEXPORT jstring JNICALL
Java_com_davidluna_architectcoders2024_app_di_MainModule_getApiKey__(JNIEnv *env, jobject pVoid) {
    UNUSED(pVoid);
    return (*env)->NewStringUTF(env, MY_API_KEY);
}

JNIEXPORT jstring JNICALL
Java_com_davidluna_architectcoders2024_app_di_MainModule_getBaseUrl__(JNIEnv *env, jobject pVoid) {
    UNUSED(pVoid);
    return (*env)->NewStringUTF(env, BASE_URL);
}
