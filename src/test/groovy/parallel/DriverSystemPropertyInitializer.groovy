package parallel

import org.apache.commons.lang3.SystemUtils

class DriverSystemPropertyInitializer {

    private static String DRIVER_BASE_PATH = getDriverPath()

    /**
     * 実行環境に合わせた各種WebDriverバイナリのパスをシステムプロパティに設定する
     */
    static void initialize() {
        initializeInternetExplorerDriver()
        initializeChromeDriver()
        initializeFirefoxDriver()
        initializeEdgeDriver()
        initializePhantomJsDriver()
    }

    static void initializeFirefoxDriver() {
        if (SystemUtils.IS_OS_WINDOWS) {
			System.setProperty("webdriver.gecko.driver", DRIVER_BASE_PATH + "/" + "geckodriver.exe")
        } else {
			System.setProperty("webdriver.gecko.driver", DRIVER_BASE_PATH + "/" + "geckodriver")
        }
    }

    static void initializeChromeDriver() {
        if (SystemUtils.IS_OS_WINDOWS) {
			System.setProperty("webdriver.chrome.driver", DRIVER_BASE_PATH + "/" + "chromedriver.exe")
        } else {
			System.setProperty("webdriver.chrome.driver", DRIVER_BASE_PATH + "/" + "chromedriver")
        }
    }

    static void initializeInternetExplorerDriver() {
        if (SystemUtils.IS_OS_WINDOWS) {
            System.setProperty("webdriver.ie.driver", DRIVER_BASE_PATH + "/" + "IEDriverServer.exe")
        }
    }

    static void initializeEdgeDriver() {
        if (SystemUtils.IS_OS_WINDOWS) {
            System.setProperty("webdriver.edge.driver", DRIVER_BASE_PATH + "/" + "MicrosoftWebDriver.exe")
        }
    }

    static void initializePhantomJsDriver() {
        if (SystemUtils.IS_OS_WINDOWS){
            System.setProperty("phantomjs.binary.path", DRIVER_BASE_PATH + "/" + "phantomjs.exe")
        } else {
            System.setProperty("phantomjs.binary.path", DRIVER_BASE_PATH + "/" + "phantomjs")
        }
    }

    private static String getDriverPath() {
        def baseDir = "driver"
        def osName
        if (SystemUtils.IS_OS_WINDOWS) {
            osName = "win"
        } else if (SystemUtils.IS_OS_MAC) {
            osName = "mac"
        } else if (SystemUtils.IS_OS_LINUX) {
            osName = "linux"
        } else {
            throw new GroovyRuntimeException("想定外のOSです。: " + SystemUtils.OS_NAME)
        }

        def archBit
        switch (SystemUtils.OS_ARCH) {
            case "amd64":
			case "x86_64":
                archBit = "64"
                break
            case "i386":
            case "x86":
                archBit = "32"
                break
            default:
                throw new GroovyRuntimeException("サポート外のアーキテクチャです。: " + SystemUtils.OS_ARCH)
        }

        return baseDir + "/" + osName + archBit
    }
}
