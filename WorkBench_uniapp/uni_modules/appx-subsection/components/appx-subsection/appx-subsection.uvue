<template>
  <view class="appx-subsection-container" 
    :style="{
      '--active-color': activeColor,
      '--inactive-color': inactiveColor,
      '--font-size': fontSize + 'px',
      '--bg-color': bgColor,
      '--bold': bold ? 'bold' : 'normal'
    }"
    :class="['appx-subsection-mode-' + mode]"
  >
    <!-- 分段器选项列表（仅支持纯字符串） -->
    <view 
      v-for="(item, index) in list" 
      :key="index"
      class="appx-subsection-item"
      :class="{ 
        'appx-subsection-item-active': activeIndex === index,
        'appx-subsection-item-inactive': activeIndex !== index
      }"
      @click="handleItemClick(index)"
    >
      <text class="appx-subsection-item-text">{{ item }}</text>
      <!-- subsection模式下的激活下划线 -->
      <view class="appx-subsection-underline" v-if="mode === 'subsection' && activeIndex === index"></view>
    </view>
  </view>
</template>

<script lang="uts" setup>

// 分段器模式类型
type AppxSubsectionMode = 'button' | 'subsection';

// Props 接口声明
interface AppxSubsectionProps {
  /** 选项数组（仅支持纯字符串格式：['选项1', '选项2']） */
  list: Array<string>;
  /** 初始化默认选中的选项索引值 */
  current?: string | number;
  /** 激活状态的颜色（文字+选中态） */
  activeColor?: string;
  /** 未激活状态的文字颜色 */
  inactiveColor?: string;
  /** 模式选择：button（按钮式，带背景）/subsection（分段式，无背景） */
  mode?: AppxSubsectionMode;
  /** 字体大小（单位px） */
  fontSize?: string | number;
  /** 激活选项的字体是否加粗 */
  bold?: boolean;
  /** 组件背景颜色（仅mode=button时有效） */
  bgColor?: string;
}

// 声明Props并设置默认值
const props = withDefaults(defineProps<AppxSubsectionProps>(), {
  current: 0,
  activeColor: '#3c9cff',
  inactiveColor: '#303133',
  mode: 'button',
  fontSize: 12,
  bold: true,
  bgColor: '#eeeeef'
});

// 定义事件：点击选项触发（向外抛出选中的索引）
const emit = defineEmits<{
  (e: 'change', index: number): void;
}>();

// 响应式激活索引
const activeIndex = ref(() => {
  const num = Number(props.current);
  if (isNaN(num) || num < 0 || num >= (props.list?.length || 0)) {
    console.warn(`appx-subsection组件：current索引${props.current}无效，已自动修正为0`);
    return 0;
  }
  return num;
});

// 监听props.current变化
watch(() => props.current, (newVal) => {
  const num = Number(newVal);
  if (!isNaN(num) && num >= 0 && num < props.list.length) {
    activeIndex.value = num;
  }
}, { immediate: true });

// 校验list合法性
if (!props.list || props.list.length === 0) {
  console.error('appx-subsection组件：list参数不能为空，请传入有效的纯字符串数组');
} else if (props.list.some(item => typeof item !== 'string')) {
  console.error('appx-subsection组件：list仅支持纯字符串数组，请勿传入对象或其他类型');
}

// 校验mode合法性
watch(() => props.mode, (val) => {
  if (!['button', 'subsection'].includes(val)) {
    console.warn(`appx-subsection组件：mode值${val}无效，已自动修正为button`);
  }
}, { immediate: true });

// 点击事件处理
const handleItemClick = (index: number) => {
  activeIndex.value = index;
  emit('change', index);
};
</script>

<style scoped>
/* 容器通用样式 */
.appx-subsection-container {
  display: flex;
  flex-direction: row;
  align-items: center;
  width: 100%;
  box-sizing: border-box;
}

/* button模式：带背景的按钮式 */
.appx-subsection-mode-button {
  background-color: var(--bg-color);
  border-radius: 4px;
  padding: 2px;
}

/* 选项通用样式（核心修复：新增flex布局保证文字居中） */
.appx-subsection-item {
  flex: 1;
  display: flex; /* 新增：让item成为flex容器 */
  justify-content: center; /* 水平居中 */
  align-items: center; /* 垂直居中 */
  text-align: center; /* 兜底：兼容行内元素 */
  cursor: pointer;
  transition: background-color 0.2s;
}

/* button模式选项样式 */
.appx-subsection-mode-button .appx-subsection-item {
  padding: 8px 0;
  border-radius: 2px;
}
/* button模式激活态 */
.appx-subsection-mode-button .appx-subsection-item-active {
  background-color: #ffffff;
  color: var(--active-color);
}
/* button模式未激活态 */
.appx-subsection-mode-button .appx-subsection-item-inactive {
  color: var(--inactive-color);
}

/* subsection模式：分段式（无背景，下划线激活） */
.appx-subsection-mode-subsection {
  background-color: transparent;
  border-bottom: 1px solid #e5e7eb;
}
/* subsection模式选项样式 */
.appx-subsection-mode-subsection .appx-subsection-item {
  padding: 10px 0;
  position: relative;
}
/* subsection模式激活态文本 */
.appx-subsection-mode-subsection .appx-subsection-item-active .appx-subsection-item-text {
  color: var(--active-color);
  font-weight: var(--bold);
}
/* subsection模式未激活态文本 */
.appx-subsection-mode-subsection .appx-subsection-item-inactive .appx-subsection-item-text {
  color: var(--inactive-color);
  font-weight: normal;
}

/* subsection模式激活下划线 */
.appx-subsection-underline {
  position: absolute;
  bottom: 0;
  left: 50%;
  transform: translateX(-50%);
  width: 60%;
  height: 2px;
  background-color: var(--active-color);
  border-radius: 1px;
  transition: width 0.2s;
}

/* 选项文本通用样式 */
.appx-subsection-item-text {
  font-size: var(--font-size);
  line-height: 1.2; /* 优化：避免文字行高导致垂直偏移 */
  display: inline-block; /* 确保text标签继承居中样式 */
}
</style>