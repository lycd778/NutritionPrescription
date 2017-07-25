package com.easyhealth365.nutritionprescription.ui.reset_password;
import com.easyhealth365.nutritionprescription.api.ApiService;
import com.easyhealth365.nutritionprescription.beans.UpdatePasswordResult;
import com.easyhealth365.nutritionprescription.utils.TLog;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lingxiao-Ching on 2017/7/24.
 */

public class ResetPasswordPresenter implements ResetPasswordContract.Presenter {
    private ResetPasswordContract.View resetPasswordView;

    public ResetPasswordPresenter(ResetPasswordContract.View resetPasswordView) {
        this.resetPasswordView = resetPasswordView;
    }

    @Override
    public void start() {

    }

    @Override
    public void resetPassword(String username, String oldpassword, String newpassword) {
        resetPasswordView.showProgress();
        Flowable<UpdatePasswordResult> resetPasswordFlowable = ApiService.resetPassword(username, oldpassword, newpassword);
        resetPasswordFlowable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UpdatePasswordResult>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(UpdatePasswordResult updatePasswordResult) {
                        TLog.log("status: " + updatePasswordResult.toString());
                        resetPasswordView.showResult(updatePasswordResult.getStatus());
                        if (100 == updatePasswordResult.getStatus()) {
                            resetPasswordView.navigateToLogin();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        resetPasswordView.hideProgress();
                        TLog.log("error: " + t.getMessage());
                        resetPasswordView.showError(t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        resetPasswordView.hideProgress();
                    }
                });
    }

    @Override
    public void onDestroy() {

    }
}
