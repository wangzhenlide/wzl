package com.wzl.cloud.domain;



import java.util.Objects;

/**
 * Oauth2获取Token返回信息封装
 * Created by macro on 2020/7/17.
 */



public class Oauth2TokenDto {
    /**
     * 访问令牌
     */
    private String token;
    /**
     * 刷新令牌
     */
    private String refreshToken;
    /**
     * 访问令牌头前缀
     */
    private String tokenHead;
    /**
     * 有效时间（秒）
     */
    private int expiresIn;

    public static  Oauth2TokenDto.Oauth2TokenDtoBuilder builder() {
        return new Oauth2TokenDto.Oauth2TokenDtoBuilder();
    }

    public Oauth2TokenDto(String token, String refreshToken, String tokenHead, int expiresIn) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.tokenHead = tokenHead;
        this.expiresIn = expiresIn;
    }

    public static class Oauth2TokenDtoBuilder {
        private String token;
        private String refreshToken;
        private String tokenHead;
        private int expiresIn;

        public Oauth2TokenDtoBuilder() {
        }
        public Oauth2TokenDto.Oauth2TokenDtoBuilder token(String token) {
            this.token = token;
            return this;
        }
        public Oauth2TokenDto.Oauth2TokenDtoBuilder refreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }
        public Oauth2TokenDto.Oauth2TokenDtoBuilder tokenHead(String tokenHead) {
            this.tokenHead = tokenHead;
            return this;
        }
        public Oauth2TokenDto.Oauth2TokenDtoBuilder expiresIn(int expiresIn) {
            this.expiresIn = expiresIn;
            return this;
        }
        public Oauth2TokenDto build() {
            return new Oauth2TokenDto(this.token, this.refreshToken, this.tokenHead, this.expiresIn);
        }

        public String toString() {
            return "Oauth2TokenDto.Oauth2TokenDtoBuilder{" +
                    "token='" + token + '\'' +
                    ", refreshToken='" + refreshToken + '\'' +
                    ", tokenHead='" + tokenHead + '\'' +
                    ", expiresIn=" + expiresIn +
                    '}';
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getTokenHead() {
        return tokenHead;
    }

    public void setTokenHead(String tokenHead) {
        this.tokenHead = tokenHead;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Oauth2TokenDto that = (Oauth2TokenDto) o;
        return expiresIn == that.expiresIn &&
                Objects.equals(token, that.token) &&
                Objects.equals(refreshToken, that.refreshToken) &&
                Objects.equals(tokenHead, that.tokenHead);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, refreshToken, tokenHead, expiresIn);
    }

}
