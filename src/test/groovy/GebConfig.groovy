import org.gebish.example.TestcontainersWebDriver
import org.testcontainers.containers.BrowserWebDriverContainer
import org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode
import org.testcontainers.containers.Network

import static org.openqa.selenium.remote.DesiredCapabilities.chrome
import static org.testcontainers.shaded.org.apache.commons.io.FileUtils.ONE_GB

reportsDir = "build/reports/geb"

driver = {
    def container = new BrowserWebDriverContainer()
            .withCapabilities(chrome())
            .withRecordingMode(VncRecordingMode.SKIP, null)
            .withNetwork(Network.SHARED)
            .withSharedMemorySize(2 * ONE_GB) as BrowserWebDriverContainer

    container.start()

    new TestcontainersWebDriver(container)
}