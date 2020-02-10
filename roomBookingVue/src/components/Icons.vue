<template>
	<div>
		<img src="../assets/logo.png">
		<p></p>
	</div>
</template>
<script>
export default {
	props:{
		text: {
			type: String,
			default: '图标'
		},
		imgSrc: {
			type: String,
			default: ''
		}

	},
	methods:{
		clickfn (){
		}
	}
}
</script>