package ir.mohajer.dto.response;

import java.io.Serializable;
import java.util.List;

public class LotteryResponse implements Serializable {

    private final List<String> name;
    private final List<LotteryFindResponse> lotteryFindResponses;

    public LotteryResponse(List<String> name, List<LotteryFindResponse> lotteryFindResponses) {
        this.name = name;
        this.lotteryFindResponses = lotteryFindResponses;
    }

    public static Builder builder(){
        return new Builder();
    }

    public List<String> getName() {
        return name;
    }

    public List<LotteryFindResponse> getLotteryFindResponses() {
        return lotteryFindResponses;
    }

    public static class Builder{

        private List<String> name;
        private List<LotteryFindResponse> lotteryFindResponses;

        private Builder(){}

        public Builder setName(List<String> name) {
            this.name = name;
            return this;
        }

        public Builder setLotteryFindResponses(List<LotteryFindResponse> lotteryFindResponses) {
            this.lotteryFindResponses = lotteryFindResponses;
            return this;
        }

        public LotteryResponse build(){
            return new LotteryResponse(name,lotteryFindResponses);
        }
    }
}
