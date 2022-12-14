package org.advancespring.trace;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TraceIdTest {
    @Test
    @DisplayName("[positive] 기본 객체를 생성하면 level=0, 8자리 UUID를 갖는다")
    void test1(){
        TraceId id = new TraceId();

        assertThat(id.getDepth()).isEqualTo(0);
        assertThat(id.getId()).hasSize(8);
    }

    @Test
    @DisplayName("[positive] 한 뎁스 깊은 객체를 생성하면 level이 1올라가고, 8자리 UUID를 갖는다")
    void test2(){
        TraceId depthZeroTrace = new TraceId();
        TraceId depthOneTrace = depthZeroTrace.createNext();

        assertThat(depthOneTrace.getDepth()).isEqualTo(depthZeroTrace.getDepth()+1);
        assertThat(depthOneTrace.getId()).hasSize(8);
        assertThat(depthOneTrace.getId()).isEqualTo(depthZeroTrace.getId());
    }

    @Test
    @DisplayName("[positive] 한 뎁스 위의 객체를 생성하면 level이 1 감소하고, 8자리 UUID를 갖는다")
    void test3(){
        TraceId depthZeroTrace = new TraceId();
        TraceId depthMinusTrace = depthZeroTrace.createPrevious();

        assertThat(depthMinusTrace.getDepth()).isEqualTo(depthZeroTrace.getDepth()-1);
        assertThat(depthMinusTrace.getId()).hasSize(8);
        assertThat(depthMinusTrace.getId()).isEqualTo(depthZeroTrace.getId());
    }
}