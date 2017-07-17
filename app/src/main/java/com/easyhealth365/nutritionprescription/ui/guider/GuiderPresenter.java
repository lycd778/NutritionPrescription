package com.easyhealth365.nutritionprescription.ui.guider;

import com.easyhealth365.nutritionprescription.api.ApiService;
import com.easyhealth365.nutritionprescription.beans.PlanID;
import com.easyhealth365.nutritionprescription.utils.TLog;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lingxiao-Ching on 2017/7/17.
 */

public class GuiderPresenter implements GuiderContract.Presenter {

    private GuiderContract.View guiderView;

    public GuiderPresenter(GuiderContract.View guiderView) {
        this.guiderView = guiderView;
    }


    @Override
    public void start() {

    }

    @Override
    public void loadPlanlist(String patientId, String access_token, String hospital_url) {
        guiderView.showProgress();
        Flowable<List<PlanID>> planListFlowable = ApiService.getPlanList(patientId, access_token, hospital_url);
        planListFlowable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<PlanID>>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(List<PlanID> IDList) {
                        if (IDList.size() == 0) {
                            guiderView.showNoplan();
                        } else {
                            for (PlanID plID : IDList) {
                                TLog.log(plID.toString());
                            }
                            guiderView.navigateToMain();
                        }

                    }

                    @Override
                    public void onError(Throwable t) {
                        guiderView.hideProgress();
                        guiderView.showError(t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        guiderView.hideProgress();
                    }
                });
    }

    @Override
    public void onDestroy() {
        TLog.log("-->GuiderPresenter  onDestroy");
        if (guiderView != null) {
            guiderView = null;
        }
    }
}
