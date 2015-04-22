package dnsExampe;

import sun.net.spi.nameservice.dns.DNSNameService;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static java.util.Arrays.asList;

/**
 * Created by Alexander on 22.04.2015.
 *
 */
public class NameServiceExample {
    public static void main(String[] args) throws Exception {
        DNSNameService dnsNameService =new DNSNameService();
        InetAddress[] result = dnsNameService.lookupAllHostAddr("google.com");
        asList(result).forEach(System.out::println);
        System.out.println();
        asList(result).forEach(inetAddress -> {
            try {
                System.out.println(dnsNameService.getHostByAddr(inetAddress.getAddress()));
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        });

    }
}
