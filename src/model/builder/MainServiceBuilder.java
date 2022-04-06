package model.builder;


import service.MainService;

public class MainServiceBuilder {

    public MainServiceBuilder() {}

    public static ISpecifyHost builder() {
        return new Impl();
    }

    private static class Impl implements
            ISpecifyHost,
            ISpecifyPort,
            IBuildMainService
    {

        private MainService mainService = new MainService();

        @Override
        public IBuildMainService withPort(int port) {
            mainService.setPort(port);
            return this;
        }

        @Override
        public ISpecifyPort withHost(String host) {
            mainService.setHost(host);
            return this;
        }

        @Override
        public MainService build() {
            return mainService;
        }

    }

}
