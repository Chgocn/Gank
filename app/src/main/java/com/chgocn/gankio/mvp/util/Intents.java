package com.chgocn.gankio.mvp.util;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.kingsmith.run.dao.DayStats;
import com.kingsmith.run.entity.QRActivity;
import com.kingsmith.run.entity.QRCode;
import com.kingsmith.run.entity.RunnerMatch;
import com.kingsmith.run.entity.TargetMode;

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
    public static final String INTENT_PREFIX = "com.kingsmith.run.";

    /**
     * Prefix for all extra data added to intents
     */
    public static final String INTENT_EXTRA_PREFIX = INTENT_PREFIX + "extra.";

    /**
     * way handle.
     */
    public static final String EXTRA_QR_CODE_STR = INTENT_EXTRA_PREFIX +"WAY";

    public static final String EXTRA_QR_ACTIVITY = INTENT_EXTRA_PREFIX +"QR_ACTIVITY";

    /**
     * result handle.
     */
    public static final String EXTRA_RESULT = INTENT_EXTRA_PREFIX +"RESULT";

    public static final String EXTRA_MARCH_EDIT = INTENT_EXTRA_PREFIX +"MARCH_EDIT";

    public static final String EXTRA_RESULT_DETAILID = INTENT_EXTRA_PREFIX +"RESULT_DETAILID";

    public static final String EXTRA_RESULT_DAYSTATS = INTENT_EXTRA_PREFIX +"RESULT_DAYSTATS";

    public static final String EXTRA_RESULT_LOCALID = INTENT_EXTRA_PREFIX +"RESULT_LOCALID";

    public static final String EXTRA_RESULT_UPLOAD = INTENT_EXTRA_PREFIX + "RESULT_UPLOAD";

    public static final String EXTRA_TARGETMODE = INTENT_EXTRA_PREFIX + "TARGET";

    public static final String EXTRA_CLIENT = INTENT_EXTRA_PREFIX + "CLIENT";

    public static final String EXTRA_DATA_PARSER = INTENT_EXTRA_PREFIX + "DATA_PARSER";

    public static final String EXTRA_QR_CODE = INTENT_EXTRA_PREFIX + "QR_CODE";


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

        public Builder qrCode(QRCode qrCode){
            add(EXTRA_QR_CODE,qrCode);
            return this;
        }

        /**
         * select run way
         * @param codeMsg
         * @return
         */
        public Builder codeMsg(String codeMsg, QRActivity qrActivity){
            add(EXTRA_QR_CODE_STR,codeMsg);
            add(EXTRA_QR_ACTIVITY,qrActivity);
            return this;
        }

        public Builder result(String detailId, long localId){
        //public Builder result(SportData sportData, String detailId, String localId){
            //add(EXTRA_RESULT,sportData);
            add(EXTRA_RESULT_DETAILID,detailId);
            add(EXTRA_RESULT_LOCALID,localId);
            //add(EXTRA_RESULT_UPLOAD,upload);
            return this;
        }

        public Builder result( long localId){
            add(EXTRA_RESULT_LOCALID,localId);
            return this;
        }

        public Builder result( DayStats dayStats){
            add(EXTRA_RESULT_DAYSTATS,dayStats);
            return this;
        }

        public Builder targetMode(TargetMode targetMode){
            add(EXTRA_TARGETMODE,targetMode);
            //add(EXTRA_CLIENT,client);
            //add(EXTRA_DATA_PARSER,dataParser);
            return this;
        }

        public Builder edit(RunnerMatch runnerMatch){
            add(EXTRA_MARCH_EDIT,runnerMatch);
            return this;
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
