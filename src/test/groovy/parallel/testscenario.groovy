package parallel

import geb.spock.GebReportingSpec
import org.openqa.selenium.JavascriptExecutor
import org.yaml.snakeyaml.Yaml
import spock.lang.Ignore
import spock.lang.IgnoreRest
import spock.lang.Shared
import spock.lang.Stepwise
import spock.lang.Unroll

import java.text.SimpleDateFormat

@Stepwise
class testscenario extends GebReportingSpec {

    @Shared
    JavascriptExecutor js = (JavascriptExecutor)browser.driver

    @Shared
	Map scenarioData = new Yaml().load(ClassLoader.getSystemResourceAsStream(
			System.getProperty("scenariospec.config")?
					System.getProperty("scenariospec.config"): "ScenarioNo0101.yml")
	).get("シナリオ実行")

  @IgnoreRest
   void login() {
        println '------------------------------------------------------'
        println 'テスト実行'
        println '------------------------------------------------------'

//        setup:
//
        where:
        入力値 <<  scenarioData.get("初期クリア")
    }
}
