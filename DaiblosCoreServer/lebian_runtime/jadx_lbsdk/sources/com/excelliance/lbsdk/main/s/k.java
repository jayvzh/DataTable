package com.excelliance.lbsdk.main.s;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;

/* JADX INFO: loaded from: classes.dex */
class k implements ai {
    private final Charset a;

    public k(Charset charset) {
        this.a = charset;
    }

    @Override // com.excelliance.lbsdk.main.s.ai
    public String a(byte[] bArr) {
        return this.a.newDecoder().onMalformedInput(CodingErrorAction.REPORT).onUnmappableCharacter(CodingErrorAction.REPORT).decode(ByteBuffer.wrap(bArr)).toString();
    }

    @Override // com.excelliance.lbsdk.main.s.ai
    public boolean a(String str) {
        CharsetEncoder charsetEncoderNewEncoder = this.a.newEncoder();
        charsetEncoderNewEncoder.onMalformedInput(CodingErrorAction.REPORT);
        charsetEncoderNewEncoder.onUnmappableCharacter(CodingErrorAction.REPORT);
        return charsetEncoderNewEncoder.canEncode(str);
    }

    @Override // com.excelliance.lbsdk.main.s.ai
    public ByteBuffer b(String str) {
        CharsetEncoder charsetEncoderNewEncoder = this.a.newEncoder();
        charsetEncoderNewEncoder.onMalformedInput(CodingErrorAction.REPORT);
        charsetEncoderNewEncoder.onUnmappableCharacter(CodingErrorAction.REPORT);
        CharBuffer charBufferWrap = CharBuffer.wrap(str);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(str.length() + ((str.length() + 1) / 2));
        while (charBufferWrap.remaining() > 0) {
            CoderResult coderResultEncode = charsetEncoderNewEncoder.encode(charBufferWrap, byteBufferAllocate, true);
            if (!coderResultEncode.isUnmappable() && !coderResultEncode.isMalformed()) {
                if (!coderResultEncode.isOverflow()) {
                    if (coderResultEncode.isUnderflow()) {
                        charsetEncoderNewEncoder.flush(byteBufferAllocate);
                        break;
                    }
                } else {
                    byteBufferAllocate = aj.a(byteBufferAllocate, 0);
                }
            } else {
                if (coderResultEncode.length() * 6 > byteBufferAllocate.remaining()) {
                    byteBufferAllocate = aj.a(byteBufferAllocate, byteBufferAllocate.position() + (coderResultEncode.length() * 6));
                }
                for (int i = 0; i < coderResultEncode.length(); i++) {
                    aj.a(byteBufferAllocate, charBufferWrap.get());
                }
            }
        }
        byteBufferAllocate.limit(byteBufferAllocate.position());
        byteBufferAllocate.rewind();
        return byteBufferAllocate;
    }
}
