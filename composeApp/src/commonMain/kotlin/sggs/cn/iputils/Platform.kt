package sggs.cn.iputils

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform