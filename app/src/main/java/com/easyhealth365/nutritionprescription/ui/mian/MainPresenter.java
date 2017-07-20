package com.easyhealth365.nutritionprescription.ui.mian;

import com.easyhealth365.nutritionprescription.api.ApiService;
import com.easyhealth365.nutritionprescription.beans.Plan;
import com.easyhealth365.nutritionprescription.beans.PlanID;
import com.easyhealth365.nutritionprescription.utils.SharedPreferenceUtil;
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
    SharedPreferenceUtil spUtils = SharedPreferenceUtil.getInstance();

    public MainPresenter(MainContract.View mainView) {
        this.mainView = mainView;
    }

    @Override
    public void start() {

    }

    @Override
    public void getPlan(String patientId, final String access_token, final String hospital_url) {
        TLog.log("获取处方列表");
        mainView.showProgress();
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
                            spUtils.setHavePlan(false);
                        } else {
                            spUtils.setHavePlan(true);
                            for (PlanID plID : IDList) {
                                TLog.log(plID.toString());
                            }
                            loadPlan(IDList.get(0).getNourishmentPlanId(), access_token, hospital_url);
                        }

                    }

                    @Override
                    public void onError(Throwable t) {
                        mainView.hideProgress();
                        mainView.showError(t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    private void loadPlan(String nid, String access_token, String hospital_url) {
        TLog.log("获取最新处方");
        Flowable<Plan> planFlowable = ApiService.getPlan(nid, access_token, hospital_url);
        planFlowable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Plan>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(Plan plan) {
                        mainView.hideProgress();
                        TLog.log(plan.toString());
                        spUtils.putPlan(plan);
                        mainView.initTabHost();
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

    @Override
    public void onDestroy() {
        TLog.log("-->MainPresenter  onDestroy");
        if (mainView != null) {
            mainView = null;
        }
    }


}
