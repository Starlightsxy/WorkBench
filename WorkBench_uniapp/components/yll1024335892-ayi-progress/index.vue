<template>
  <view class="ayi-progress">
    <view class="ayi-progress-bar" v-if="type === 'line'">
      <view class="ayi-progress-bar__outer" :style="{ height: setRpx(strokeWidth), backgroundColor: unColor }">
        <view class="ayi-progress-bar__inner" :style="{ backgroundColor, width }"></view>
      </view>
    </view>
    <slot name="text">
      <view class="ayi-progress__text" v-if="showText">
        <template v-if="!status">{{ value }}%</template>
        <text class="ayi-progress__icon" v-else :class="icon"></text>
      </view>
    </slot>
  </view>
</template>
<script lang="ts" setup>
/**
 * @description 进度条，支持多种颜色显示
 * @property {Number} value 绑定值
 * @property {String} type 类型
 * @property {String} strokeWidth 线条宽度
 * @property {String} showText 是否显示文本
 * @property {String} color 线条颜色, 支持多色
 * @property {String} status 状态
 * @property {Boolean} icon 尾部图标
 * @example <ayi-progress :value="40"></ayi-progress>
 */
import { computed } from "vue"
import { setRpx, getCurrentColor } from "./tools"
const props = defineProps({
	value: {
		type: Number,
		default: 0,
		required: true
	},
	type: {
		type: String,
		default: "line"
	},
	strokeWidth: {
		type: Number,
		default: 12
	},
	showText: {
		type: Boolean,
		default: true
	},
	color: {
		type: [String, Array],
		default: ""
	},
	unColor: {
		type: String,
		default: "#ebeef5"
	},
	status: {
		type: Boolean
	},
	icon: String
})
// 宽度
const width = computed(() => {
	if (props.value > 100) {
		return "100%"
	} else if (props.value < 0) {
		return 0
	} else {
		return `${props.value}%`
	}
})
// 背景颜色
const backgroundColor = computed(() => {
	return getCurrentColor({
		value: props.value,
		color: props.color,
		max: 100
	})
})
</script>
<style lang="scss" scoped>
@import "./index.scss";
</style>
