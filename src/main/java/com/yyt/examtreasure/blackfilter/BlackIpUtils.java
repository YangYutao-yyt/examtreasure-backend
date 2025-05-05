package com.yyt.examtreasure.blackfilter;

import cn.hutool.bloomfilter.BitMapBloomFilter;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;

import java.util.List;
import java.util.Map;

/**
 * 黑名单过滤工具类
 *
 */
@Slf4j
public class BlackIpUtils {

    private static BitMapBloomFilter bloomFilter = new BitMapBloomFilter(100);

    // 判断 ip 是否在黑名单里
    public static boolean isBlackIp(String ip) {
        return bloomFilter.contains(ip);
    }

    /**
     * 重建 ip 黑名单
     *
     * @param configInfo
     */
    public static void rebuildBlackIp(String configInfo) {
        if (StrUtil.isBlank(configInfo)) {
            configInfo = "{}";
        }
        // 解析 yaml 文件
        Yaml yaml = new Yaml();
        Map map = yaml.loadAs(configInfo, Map.class);
        // 获取 IP 黑名单
        List<String> blackIpList = (List<String>) map.get("blackIpList");
        // 加锁防止并发
        synchronized (BlackIpUtils.class) {
            if (CollUtil.isNotEmpty(blackIpList)) {
                // 注意构造参数的设置
                // 动态计算预期元素数量，可根据需要调整扩容系数（例如1.5倍）
                int expectedElements = blackIpList.size();
                BitMapBloomFilter bitMapBloomFilter = new BitMapBloomFilter(expectedElements*10);
                for (String blackIp : blackIpList) {
                    bitMapBloomFilter.add(blackIp);
                }
                bloomFilter = bitMapBloomFilter;
            } else {
                bloomFilter = new BitMapBloomFilter(100);
            }
        }
    }
}
