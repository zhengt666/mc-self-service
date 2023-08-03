package com.cpcnet.mc.mcgateway.handler;

import com.cpcnet.component.common.exception.DefaultBusinessException;
import com.cpcnet.component.common.resp.ResponseCode;
import com.cpcnet.component.common.resp.ResponseResult;
import com.cpcnet.component.common.utils.json.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @description: 异常处理类
 * @author: Ebon Zheng
 * @create: 2023-08-01 15:20
 **/
@Slf4j
@Order(-1)
public class GlobalExceptionHandler implements ErrorWebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        log.error("gateway error {}, cause:", ex.getMessage(), ex);

        ServerHttpResponse response = exchange.getResponse();

        if (response.isCommitted()) {
            return Mono.error(ex);
        }

        // header set
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        if (ex instanceof ResponseStatusException) {
            response.setStatusCode(((ResponseStatusException) ex).getStatus());
        }
        Integer code = ResponseCode.FAIL.getCode();
        if (ex instanceof DefaultBusinessException) {
            code = ((DefaultBusinessException) ex).getResponseCode();
        }
        Integer finalCode = code;
        return response.writeWith(Mono.fromSupplier(() -> {
            DataBufferFactory bufferFactory = response.bufferFactory();
            DataBuffer dataBuffer = null;
            try {
                byte[] bytes = JsonUtils.toJSONString(ResponseResult.fail(finalCode, ex.getMessage()))
                        .getBytes();
                dataBuffer = bufferFactory.wrap(bytes);
                return dataBuffer;
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Error writing response", ex);
                dataBuffer = bufferFactory.wrap(new byte[0]);
                return dataBuffer;
            } finally {
                dataBuffer = bufferFactory.allocateBuffer();
                DataBufferUtils.release(dataBuffer);
            }
        }));
    }
}
