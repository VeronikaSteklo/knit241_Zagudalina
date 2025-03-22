package org.knit.solutions.semestr1.lab1;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/***
 * Задача 2
 * Создайте класс SimpleUrl
 * глядя на URL ниже создайте как можно больше полей в классе SimpleUrl:
 * https://test.ru/test/1072/page.jsp?intParam=12345&doubleParam=3.14&textParameter=someText
 * добавьте геттеры и сеттеры для полей
 * Переопределите метод toString() для вывода информации о полях класса:
 * protocol = https
 * address = test.ru
 * domainZone = ru
 * siteName = test
 * webpageName = page.jsp
 * webPageExtention = jsp
 * ....
 * Распарсите данный URL на переменные, создайте экземпляр класса SimpleUrl и выведите на экран
 * Дополнительно
 * попробуйте с другими URL в сети, подумайте как можно сгруппировать значения.
 *
 */
public class Task2 {
    public class SimpleUrl {
        private String protocol;
        private String siteName;
        private String domainZone;
        private String[] pathElements;
        private String pageName;
        private String fileExtension;
        private Map<String, String> parameters = new LinkedHashMap<>();
        private String anchor;

        public SimpleUrl(String url) {
            // Protocol
            this.protocol = url.split("://")[0];
            String[] splittedUrlWithoutProtocol = url.split("://")[1].split("/");

            // Site name and domain
            String fullAddress = splittedUrlWithoutProtocol[0];
            int addressLastDotIndex = fullAddress.lastIndexOf('.');
            this.domainZone = fullAddress.substring(addressLastDotIndex + 1);
            this.siteName = fullAddress.substring(0, addressLastDotIndex);

            // Path elements
            this.pathElements = Arrays.copyOfRange(splittedUrlWithoutProtocol, 1, splittedUrlWithoutProtocol.length - 1);

            String urlEnd = splittedUrlWithoutProtocol[splittedUrlWithoutProtocol.length - 1];

            // Page name
            String fullPageName = urlEnd.split("\\?")[0];
            if (!fullPageName.contains("."))
                this.pageName = fullPageName;
            else {
                this.pageName = fullPageName.split("\\.")[0];
                this.fileExtension = fullPageName.split("\\.")[1];
            }

            // Anchor
            if (urlEnd.contains("#")) {
                this.anchor = urlEnd.split("#")[1];
                urlEnd = urlEnd.split("#")[0];
            }

            // Params
            if (urlEnd.contains("?")) {
                String urlParameters = urlEnd.split("\\?")[1];
                for (String paramWithKey : urlParameters.split("&")) {
                    this.parameters.put(paramWithKey.split("=")[0], paramWithKey.split("=")[1]);
                }
            }
        }

        public String getProtocol() {
            return this.protocol;
        }

        public void setProtocol(String protocol) {
            this.protocol = protocol;
        }

        public String getSiteName() {
            return siteName;
        }

        public void setSiteName(String siteName) {
            this.siteName = siteName;
        }

        public String getDomainZone() {
            return domainZone;
        }

        public void setDomainZone(String domainZone) {
            this.domainZone = domainZone;
        }

        public String[] getPathElements() {
            return pathElements;
        }

        public String getJoinedPathElements() {
            StringBuilder result = new StringBuilder();
            for (String element : pathElements) {
                result.append(element);
                result.append("/");
            }
            return result.toString();
        }

        public void setPathElements(String[] pathElements) {
            this.pathElements = pathElements;
        }

        public String getPageName() {
            return pageName;
        }

        public void setPageName(String pageName) {
            this.pageName = pageName;
        }

        public String getFileExtension() {
            return fileExtension;
        }

        public void setFileExtension(String fileExtension) {
            this.fileExtension = fileExtension;
        }

        public Map<String, String> getParameters() {
            return parameters;
        }

        public String getJoinedParameters() {
            StringBuilder result = new StringBuilder();
            for (String key : parameters.keySet()) {
                result.append(key);
                result.append("=");
                result.append(parameters.get(key));
                result.append("&");
            }
            result.deleteCharAt(result.length() - 1);
            return result.toString();
        }

        public void setParameters(Map<String, String> parameters) {
            this.parameters = parameters;
        }

        public void addParameter(String key, String value) {
            this.parameters.put(key, value);
        }

        public void deleteParameter(String key) {
            this.parameters.remove(key);
        }

        public String getAnchor() {
            return anchor;
        }

        public void setAnchor(String anchor) {
            this.anchor = anchor;
        }

        @Override
        public String toString() {
            return protocol + "://" +
                    siteName + "." + domainZone + "/" +
                    getJoinedPathElements() + pageName +
                    (fileExtension == null ? "" : "." + fileExtension) +
                    (parameters.isEmpty() ? "/" : "?" + getJoinedParameters()) +
                    (anchor == null ? "" : "#" + anchor);
        }
    }

    public void execute() {

        String urlValue = "https://test.ru/test/1072/page.jsp?intParam=12345&doubleParam=3.14&textParameter=someText";

        SimpleUrl simpleUrl = new SimpleUrl(urlValue);
        System.out.print(simpleUrl);
        System.out.println(" " + simpleUrl.toString().equals(urlValue));

        simpleUrl.setFileExtension(null);
        System.out.println(simpleUrl);

        simpleUrl.addParameter("newParameter", "someValue");
        System.out.println(simpleUrl);

        simpleUrl.deleteParameter("notExisting");
        System.out.println(simpleUrl);

        simpleUrl.deleteParameter("doubleParam");
        System.out.println(simpleUrl);

        System.out.println("Url для теста: ");

        String[] testUrlValues = new String[]{
                "https://developer.mozilla.org/en-US/search?q=URL",
                "https://developer.mozilla.org/en-US/docs/Learn/",
                "https://www.geeksforgeeks.org/array-data-structure/?ref=home-articlecards#what-is-array"
        };

        for (String testUrlValue : testUrlValues) {
            SimpleUrl testSimpleUrl = new SimpleUrl(testUrlValue);
            System.out.print(testSimpleUrl);
            System.out.println(" " + testSimpleUrl.toString().equals(testUrlValue));
        }
    }
}
