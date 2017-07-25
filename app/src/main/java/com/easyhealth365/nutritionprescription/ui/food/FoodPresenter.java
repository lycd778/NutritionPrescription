package com.easyhealth365.nutritionprescription.ui.food;

import com.easyhealth365.nutritionprescription.api.ApiService;
import com.easyhealth365.nutritionprescription.beans.PreRecord;
import com.easyhealth365.nutritionprescription.beans.Record;
import com.easyhealth365.nutritionprescription.beans.UpdateRecordResult;
import com.easyhealth365.nutritionprescription.utils.SharedPreferenceUtil;
import com.easyhealth365.nutritionprescription.utils.TLog;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lingxiao-Ching on 2017/7/20.
 */

public class FoodPresenter implements FoodContract.Presenter {
    private FoodContract.View foodView;

    public FoodPresenter(FoodContract.View foodView) {
        this.foodView = foodView;
    }

    SharedPreferenceUtil spUtils = SharedPreferenceUtil.getInstance();

    @Override
    public void start() {

    }

    @Override
    public void updateRecord(Record record, String patientId, String access_token, String hospital_url) {
       // foodView.showLoading();
        Flowable<UpdateRecordResult> updateRecordFlowable = ApiService.updateRecord(record, patientId, access_token, hospital_url);
        updateRecordFlowable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UpdateRecordResult>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(UpdateRecordResult updateRecordResult) {
                        //foodView.dismissLoading();
                            TLog.log("updateFoodresult: " + updateRecordResult.getState());
                            foodView.showUpdateResult(updateRecordResult.getState());
                    }

                    @Override
                    public void onError(Throwable t) {
                        //foodView.dismissLoading();
                        foodView.showError(t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                       // foodView.dismissLoading();
                    }
                });
    }

    @Override
    public void getPreRecord(String checkTime, String patientId, String access_token, String hospital_url) {
        foodView.showLoading();
        Flowable<PreRecord> getPreRecordFlowable = ApiService.getPreRecord(checkTime, patientId, access_token, hospital_url);
        getPreRecordFlowable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PreRecord>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(PreRecord preRecord) {
                        foodView.dismissLoading();
                        TLog.log("updateFoodresult: " + preRecord.toString());
                        foodView.reLoadActualRecord(preRecord);
                    }

                    @Override
                    public void onError(Throwable t) {
                        foodView.dismissLoading();
                        foodView.showError(t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        foodView.dismissLoading();
                    }
                });
    }

    @Override
    public void onDestroy() {

    }
}
