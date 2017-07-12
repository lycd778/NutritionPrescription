package com.easyhealth365.nutritionprescription.ui.mian;

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
 * Created by Administrator on 2016/7/7.
 */
public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mainView;

    public MainPresenter(MainContract.View mainView) {
        this.mainView=mainView;
    }

    @Override
    public void start() {
        mainView.initTabHost();
    }

    @Override
    public void onDestroy() {
        TLog.log("-->MainPresenter  onDestroy");
        if (mainView!=null){
            mainView=null;
        }
    }

    @Override
    public void loadPlanlist(String patientId, String access_token,String hospital_url) {
        mainView.showProgress();
        Flowable<List<PlanID>> planListFlowable = ApiService.getPlanList(patientId, access_token,hospital_url);
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
                        for(PlanID plID:IDList){
                            TLog.log(plID.toString());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        mainView.hideProgress();
                        mainView.showError(t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        mainView.hideProgress();
                    }
                });
    }
}
