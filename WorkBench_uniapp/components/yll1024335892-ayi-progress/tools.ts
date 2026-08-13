function getTag(value) {
	if (value == null) {
		return value === undefined ? "[object Undefined]" : "[object Null]"
	}
	return toString.call(value)
}

function isObjectLike(value) {
	return typeof value === "object" && value !== null
}

export function isNumber(value) {
	return typeof value === "number" || (isObjectLike(value) && getTag(value) == "[object Number]")
}

export function isBoolean(value) {
	return typeof value === "boolean"
}

export function isArray(value) {
	return Array.isArray(value)
}
export function setRpx(val : any) : string {
	return isArray(val) ? val.map(setRpx).join(" ") : isNumber(val) ? `${val}rpx` : val
}
export function isString(value) {
	return typeof value === "string"
}
export function getCurrentColor({ color, max, value } : any) {
	if (isString(color)) {
		return color
	} else {
		const colorArray = color
			.map((item : any, index : number) => {
				if (isString(item)) {
					return {
						color: item,
						value: (index + 1) * (max / color.length)
					}
				}
				return item
			})
			.sort((a : any, b : any) => a.value - b.value)

		for (let i = 0; i < colorArray.length; i++) {
			if (colorArray[i].value >= value) {
				return colorArray[i].color
			}
		}
		return colorArray[colorArray.length - 1].color
	}
}