package discovery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.engine.descriptor.MethodBasedTestDescriptor;
import org.junit.platform.engine.DiscoverySelector;
import org.junit.platform.engine.FilterResult;
import org.junit.platform.engine.discovery.ClassNameFilter;
import org.junit.platform.engine.discovery.DiscoverySelectors;
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
 * @Description 收集测试用例
 * @createTime 2022年11月30日 10:44:00
 */
public class DiscoveryTest {
    @Test
    @DisplayName("Should select only the desired method(s)")
    void doTEst() {
        var methods = discover(
                DiscoverySelectors.selectPackage("top.testeru"));
        System.out.println(methods);
//                ,
//                () -> {
//                    // your way
//                    var fqmn = "io.github.zforgo.stackoverflow.ClassWithTestInfo#foo(TestInfo)";
//                    var methods = discover(DiscoverySelectors.selectMethod(fqmn));
//                    Assertions.assertEquals(0, methods.size());
//                },
//                () -> {
//                    // good way
//                    var fqmn = "io.github.zforgo.stackoverflow.ClassWithTestInfo#foo(org.junit.jupiter.api.TestInfo)";
//                    var methods = discover(DiscoverySelectors.selectMethod(fqmn));
//                    Assertions.assertEquals(1, methods.size());
//                }
//        );
    }

    private List<Method> discover(DiscoverySelector... selectors) {
        final List<Method> methodCollector = new ArrayList<>();
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(selectors)
                .filters((PostDiscoveryFilter) object -> {
                    Method m = ((MethodBasedTestDescriptor) object).getTestMethod();
                    methodCollector.add(m);
//                    ClassNameFilter.includeClassNamePatterns(".*_Test")
                    return FilterResult.included("Matched");
                })
                .build();
        LauncherFactory.create().discover(request);

        return methodCollector;
    }
}
