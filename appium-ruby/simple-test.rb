require 'rspec'
require 'selenium-webdriver'

APP_PATH = '../GoClock/build/apk/GoClock-debug-unaligned.apk'


def absolute_app_path
    file = File.join(File.dirname(__FILE__), APP_PATH)
    raise "App doesn't exist #{file}" unless File.exist? file
    file
end

capabilities = {
  'device' => 'Android',
  'browserName' => '',
  'version' => '4.2',
  'app' => absolute_app_path,
  'app-package' => 'com.example.goclock',
  'app-activity' => '.MainActivity'
}

server_url = "http://127.0.0.1:4723/wd/hub"

describe "GoClock" do
  before :all do
    @driver = Selenium::WebDriver.for(:remote, :desired_capabilities => capabilities, :url => server_url)
    @driver.manage.timeouts.implicit_wait = 10 # seconds
   end

  after :all do
    @driver.quit
  end

  it "should show Done after start" do
    elements = @driver.find_elements :tag_name, :button
    element = elements[0]
    element.text.should eq("Start")
    element.click
    element.text.should eq("Done")
  end
  
  # A test which fails.
  it "should show Hoge after start" do
    elements = @driver.find_elements :tag_name, :button
    element = elements[0]
    element.text.should eq("Start")
    element.click
    element.text.should eq("Hoge")
  end
end
