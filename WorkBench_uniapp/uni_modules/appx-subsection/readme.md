# appx-subsection 分段器组件帮助文档
## 组件介绍
appx-subsection 是一款适用于多端（基于uts语法）的分段器组件，支持按钮式（button）和分段式（subsection）两种展示模式，可自定义文字颜色、字体大小、激活态加粗等样式，常用于分类切换、选项筛选等场景。

## 组件属性（Props）
| 属性名 | 类型 | 默认值 | 必填 | 说明 |
|--------|------|--------|------|------|
| list | Array<string> | - | 是 | 选项数组，仅支持纯字符串格式，如 `['选项1', '选项2']`；若传入空数组或非字符串元素，控制台会输出对应错误提示 |
| current | string / number | 0 | 否 | 初始化默认选中的选项索引值；若传入无效索引（如非数字、超出数组长度），控制台会输出警告并自动修正为0 |
| activeColor | string | #3c9cff | 否 | 激活状态的颜色，包含文字颜色（所有模式）、button模式激活项背景色外的文字色、subsection模式激活下划线颜色 |
| inactiveColor | string | #303133 | 否 | 未激活状态的文字颜色 |
| mode | 'button' / 'subsection' | button | 否 | 组件展示模式：<br>- button：按钮式，带整体背景色，激活项有白色背景；<br>- subsection：分段式，无整体背景，底部有边框，激活项下方显示下划线；<br>若传入非有效值，控制台会输出警告并自动修正为button |
| fontSize | string / number | 12 | 否 | 字体大小，单位为px，组件内部会自动拼接px单位 |
| bold | boolean | true | 否 | 仅subsection模式生效，控制激活选项的字体是否加粗 |
| bgColor | string | #eeeeef | 否 | 仅button模式生效，控制组件整体背景颜色 |

## 组件事件（Events）
| 事件名 | 触发时机 | 回调参数 | 说明 |
|--------|----------|----------|------|
| change | 点击分段器选项时 | index: number | 返回当前选中选项的索引值；同时组件内部会更新激活索引，同步展示选中状态 |

## 组件样式说明
### 通用样式
- 容器默认宽度100%，采用flex横向布局，选项均分宽度（flex:1）；
- 选项文字默认水平/垂直居中，行高优化避免文字偏移；
- 所有交互样式过渡动画时长为0.2s（背景色、下划线宽度）。

### 不同模式样式差异
| 模式 | 视觉特征 | 激活态表现 |
|------|----------|------------|
| button | 整体带背景色（bgColor），选项有内边距和圆角 | 激活项背景为白色，文字色为activeColor；未激活项文字色为inactiveColor |
| subsection | 无整体背景，底部有浅灰色边框 | 激活项文字色为activeColor（可加粗），下方显示activeColor的下划线；未激活项文字色为inactiveColor |

## 使用示例
### 基础使用（button模式）
```vue
<template>
  <appx-subsection 
    :list="['推荐', '热点', '财经']" 
    :current="1"
    @change="handleChange"
  />
</template>

<script lang="uts" setup>
const handleChange = (index: number) => {
  console.log('选中索引：', index);
};
</script>
```

### 自定义样式（subsection模式）
```vue
<template>
  <appx-subsection 
    :list="['全部', '待付款', '待发货', '已完成']" 
    mode="subsection"
    activeColor="#ff6700"
    inactiveColor="#666666"
    :fontSize="14"
    :bold="false"
    @change="handleChange"
  />
</template>

<script lang="uts" setup>
const handleChange = (index: number) => {
  console.log('选中索引：', index);
};
</script>
```

## 注意事项
1. list参数必须传入非空的纯字符串数组，否则组件会输出错误提示且无法正常渲染；
2. current参数建议传入非负整数，若传入字符串类型的数字（如"2"），组件会自动转换为数字；
3. mode参数仅支持'button'和'subsection'两个值，传入其他值会自动修正为button并输出警告；
4. bgColor仅对button模式生效，bold仅对subsection模式生效，设置前需确认组件模式；
5. 组件宽度默认100%，可通过父容器控制宽度，内部选项会自动均分宽度。
