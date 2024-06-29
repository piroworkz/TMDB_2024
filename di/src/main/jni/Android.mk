LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
MY_API_KEY := $(strip $(MY_API_KEY))
BASE_URL := $(strip $(BASE_URL))
LOCAL_CFLAGS += -DMY_API_KEY=\"$(MY_API_KEY)\"
LOCAL_CFLAGS += -DBASE_URL=\"$(BASE_URL)\"
LOCAL_MODULE    := native
LOCAL_SRC_FILES := native.c
include $(BUILD_SHARED_LIBRARY)