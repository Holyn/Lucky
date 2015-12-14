package com.dianxun.holyn.lucky.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dianxun.holyn.lucky.LuckyApplication;
import com.dianxun.holyn.lucky.view.module.ActivityModule;

import java.util.List;

import butterknife.ButterKnife;
import dagger.ObjectGraph;

/**
 * Created by holyn on 2015/12/10.
 */
public abstract class BaseActivity extends AppCompatActivity {
    private ObjectGraph activityScopeGraph;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        injectDependencies();
        injectViews();
    }

    protected  abstract  int getLayoutResId();

    /**
     * Method used to resolve dependencies provided by Dagger modules. Inject an object to provide
     * every @Inject annotation contained.
     *
     * @param object to inject.
     */
    public void inject(Object object) {
        activityScopeGraph.inject(object);
    }

    /**
     * Get a list of Dagger modules with Activity scope needed to this Activity.
     *
     * @return modules with new dependencies to provide.
     */
    protected abstract List<Object> getModules();

    /**
     * Create a new Dagger ObjectGraph to add new dependencies using a plus operation and inject the
     * declared one in the activity. This new graph will be destroyed once the activity lifecycle
     * finish.
     *
     * This is the key of how to use Activity scope dependency injection.
     */
    private void injectDependencies() {
        LuckyApplication luckyApplication = (LuckyApplication)BaseActivity.this.getApplication();
        List<Object> activityScopeModules = getModules();
        activityScopeModules.add(new ActivityModule(this));
        activityScopeGraph = luckyApplication.plus(activityScopeModules);
        inject(this);
    }

    /**
     * Replace every field annotated with ButterKnife annotations like @InjectView with the proper
     * value.
     */
    private void injectViews() {
        ButterKnife.bind(this);
    }
}
