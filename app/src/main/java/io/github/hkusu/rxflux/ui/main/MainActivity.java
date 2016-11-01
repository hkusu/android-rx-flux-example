package io.github.hkusu.rxflux.ui.main;

import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.github.hkusu.rxflux.R;
import io.github.hkusu.rxflux.di.AppComponent;
import io.github.hkusu.rxflux.lib.flux.Action;
import io.github.hkusu.rxflux.ui.RxFluxApplication;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.messageText)
    TextView messageText;
    @BindView(R.id.countText)
    TextView countText;
    @BindView(R.id.countUpButton)
    Button countUpButton;
    @BindView(R.id.countDownButton)
    Button countDownButton;

    private MainStore mainStore;
    private MainActionCreator mainActionCreator;
    private Unbinder unbinder; // ButterKnife

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initDependencies();
        setupView();
    }

    private void initDependencies() {
        AppComponent appComponent = ((RxFluxApplication) getApplication()).getAppComponent();
        mainStore = appComponent.mainStore();
        mainActionCreator = appComponent.mainActionCreator();
    }

    private void setupView() {
        unbinder = ButterKnife.bind(this); // ButterKnife
    }

    @MainThread
    private void updateUI(Action action) {
        if (isDestroyed()) {
            return;
        }
        // NOTE: パフォーマンスが気になる場合はactionで場合分けしたり前状態と比較の上で更新したりした方がいいかもしれない
        countText.setText(mainStore.getCount().toString());
        messageText.setText(mainStore.getMessage());
    }

    @Override
    protected void onResume() {
        super.onResume();

        mainStore.observeOnMainThread(action -> {
            // Log.d("posted action:", action.key);
            updateUI(action);
        });

        if (!mainStore.isInitialized()) {
            mainActionCreator.initView();
        } else {
            updateUI(null);
            // ActionCreator経由だと直前行の購読開始がタイミング的に間に合わないケースがあったので
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mainStore.clear();
        // 購読停止しないとActivityが破棄されたがメモリに残ってるケースでStoreの変更通知を受取り
        // 仮にUIスレッドを操作してると落ちてしまう
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind(); // ButterKnife
    }

    // [+]ボタン押下
    @OnClick(R.id.countUpButton)
    public void onCountUpButtonClick() {
        mainActionCreator.countUp(1);
    }

    // [-]ボタン押下
    @OnClick(R.id.countDownButton)
    public void onCountDownButtonClick() {
        mainActionCreator.countDown(1);
    }
}
