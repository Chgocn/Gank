package com.chgocn.gankio.mvp.util;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Helper for creating intents
 *
 * @author chgocn(chgocn@gmail.com)
 */
public class Intents {

    /**
     * Prefix for all intents created
     */
    public static final String INTENT_PREFIX = "com.chgocn.gankio.mvp.";

    /**
     * Prefix for all extra data added to intents
     */
    public static final String INTENT_EXTRA_PREFIX = INTENT_PREFIX + "extra.";


    /**
     * Builder for generating an intent configured with extra data
     */
    public static class Builder {

        private final Intent intent;

        /**
         * Create builder without suffix
         *
         */
        public Builder() {
            intent = new Intent(INTENT_PREFIX + INTENT_PREFIX);
        }

        /**
         * Create builder with suffix
         *
         * @param actionSuffix
         */
        public Builder(String actionSuffix) {
            // actionSuffix = e.g. "symbol.VIEW"
            intent = new Intent(INTENT_PREFIX + actionSuffix);
        }

        // [+] base method
        /**
         * Add extra field data value to intent being built up
         *
         * @param fieldName
         * @param value
         * @return this builder
         */
        public Builder add(String fieldName, String value) {
            intent.putExtra(fieldName, value);
            return this;
        }

        /**
         * Add extra field data values to intent being built up
         *
         * @param fieldName
         * @param values
         * @return this builder
         */
        public Builder add(String fieldName, CharSequence[] values) {
            intent.putExtra(fieldName, values);
            return this;
        }

        /**
         * Add extra field data value to intent being built up
         *
         * @param fieldName
         * @param value
         * @return this builder
         */
        public Builder add(String fieldName, int value) {
            intent.putExtra(fieldName, value);
            return this;
        }

        /**
         * Add extra field data value to intent being built up
         *
         * @param fieldName
         * @param values
         * @return this builder
         */
        public Builder add(String fieldName, int[] values) {
            intent.putExtra(fieldName, values);
            return this;
        }

        /**
         * Add extra field data value to intent being built up
         *
         * @param fieldName
         * @param values
         * @return this builder
         */
        public Builder add(String fieldName, boolean[] values) {
            intent.putExtra(fieldName, values);
            return this;
        }

        /**
         * Add extra field data value to intent being built up
         *
         * @param fieldName
         * @param value
         * @return this builder
         */
        public Builder add(String fieldName, Serializable value) {
            intent.putExtra(fieldName, value);
            return this;
        }

        public Builder add(Bundle bundle){
            intent.putExtras(bundle);
            return this;
        }

        /**
         * Add extra field data value to intent being built up
         *
         * @param fieldName
         * @param value
         * @return this builder
         */
        public Builder add(String fieldName, Parcelable value) {
            intent.putExtra(fieldName, value);
            return this;
        }

        /**
         * Get built intent
         *
         * @return intent
         */
        public Intent toIntent() {
            return intent;
        }
        // [-] base method
    }
}
