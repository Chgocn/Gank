/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.chgocn.gankio.mvp.main.data.api;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import java.io.IOException;

import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.Okio;
import retrofit2.Converter;

import static android.content.ContentValues.TAG;

final class CGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    CGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        JsonReader jsonReader = gson.newJsonReader(value.charStream());
        try {
            BufferedSource bufferedSource = Okio.buffer(value.source());
            String body = bufferedSource.readUtf8();
            bufferedSource.close();
            Log.e(TAG, "body : " + body);

            JsonObject json = gson.fromJson(body, JsonObject.class);
            if (json.get("ret").getAsInt() == 200) {
                return adapter.fromJson(json.get("data").getAsJsonObject().toString());
            } else {
                throw new HttpApiException(json.get("ret").getAsInt(), json.get("msg").getAsString());
            }
        } finally {
            value.close();
        }
    }
}
