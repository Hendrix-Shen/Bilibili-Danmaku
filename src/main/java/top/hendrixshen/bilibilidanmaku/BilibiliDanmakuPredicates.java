package top.hendrixshen.bilibilidanmaku;

import top.hendrixshen.bilibilidanmaku.config.Configs;
import top.hendrixshen.magiclib.config.Option;
import top.hendrixshen.magiclib.dependency.annotation.OptionDependencyPredicate;

public class BilibiliDanmakuPredicates {
    public static class DebugOptionPredicate implements OptionDependencyPredicate {
        @Override
        public boolean test(Option option) {
            return Configs.debugMode;
        }
    }
}
