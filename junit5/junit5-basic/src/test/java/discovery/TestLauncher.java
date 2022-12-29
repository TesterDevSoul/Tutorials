package discovery;

import org.junit.jupiter.engine.descriptor.MethodBasedTestDescriptor;
import org.junit.platform.engine.FilterResult;
import org.junit.platform.engine.discovery.ClassNameFilter;
import org.junit.platform.launcher.*;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.platform.engine.discovery.ClassNameFilter.includeClassNamePatterns;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project junit5-tutorial
 * @Description
 * @createTime 2022年11月30日 14:18:00
 */
public class TestLauncher {
    public static void main(String[] args) {
        final List<Method> methodCollector = new ArrayList<>();

        LauncherDiscoveryRequest request
                = LauncherDiscoveryRequestBuilder.request()
                .selectors(selectPackage("top.testeru.basic"))
                .filters(
//                        (PostDiscoveryFilter) object -> {
//                            Method m = ((MethodBasedTestDescriptor) object).getTestMethod();
//                            methodCollector.add(m);
//                            return FilterResult.included("_Test");

                            //通过对类名的通配符匹配，制定或者排除相关用例
                        includeClassNamePatterns(".*Test"))
//                           return includeClassNamePatterns(".*_Test");
//                        })
//                .configurationParameter(
//                        "junit.conditions.deactivate",
//                        "com.baeldung.extensions.*")
                .build();
        System.out.println(request);
        TestPlan plan = LauncherFactory.create().discover(request);
        Launcher launcher = LauncherFactory.create();
        SummaryGeneratingListener summaryGeneratingListener
                = new SummaryGeneratingListener();
        launcher.execute(
                request,
                new TestExecutionListener[] { summaryGeneratingListener });

        System.out.println(summaryGeneratingListener.getSummary());
    }
}