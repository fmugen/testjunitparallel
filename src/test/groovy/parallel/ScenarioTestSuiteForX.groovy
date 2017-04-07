package parallel

import junit.framework.JUnit4TestAdapter
import junit.framework.Test
import junit.framework.TestSuite
import org.yaml.snakeyaml.Yaml

class ScenarioTestSuiteForX {

    static Test suite() {
        def jobconfig = System.getProperty("scenariospec.jobconfig") ?
                System.getProperty("scenariospec.jobconfig") : "job1.yml"
        def jobData = new Yaml().load(ClassLoader.getSystemResourceAsStream(jobconfig)).get("jenkinsjob")

        TestSuite suite = new TestSuite()

        jobData.iterator().each() {
            def senarioName = it.get("シナリオ名")
            println senarioName
            Class senarioClass = Class.forName(it.get("シナリオクラス"))
            suite.addTest(new JUnit4TestAdapter(senarioClass) {
                @Override
                void run(junit.framework.TestResult result) {
                    System.setProperty("scenariospec.config", senarioName)
                    super.run(result)
                }
            })
        }
        return suite
    }
}