import Vue from 'vue'
import Router from 'vue-router'
// import Homepage from '@/components/Homepage'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/vpn'
    },
    // {
    //   path: '/homepage',
    //   name: 'Homepage',
    //   component: () => import('@/views/Homepage'),
    //   meta: {
    //     requireAuth: false,
    //     title: '开发七部会议室预定'
    //   }
    // },
    // {
    //   path: '/newBooking',
    //   name: 'NewBooking',
    //   component: () => import('@/views/NewBooking'),
    //   meta: {
    //     requireAuth: true,
    //     title: '新建预定'
    //   }
    // },
    // {
    //   path: '/toolbox',
    //   name: 'Toolbox',
    //   component: () => import('@/views/Toolbox'),
    //   meta: {
    //     requireAuth: true,
    //     title: '工具箱'
    //   }
    // },
    // {
    //   path: '/myPage',
    //   name: 'MyPage',
    //   component: () => import('@/views/MyPage'),
    //   meta: {
    //     requireAuth: true,
    //     title: '我的信息'
    //   }
    // },
    // {
    //   path: '/roomDetail/:roomIdInit',
    //   name: 'RoomDetail',
    //   component: () => import('@/views/RoomDetail'),
    //   props: true,
    //   meta: {
    //     requireAuth: true,
    //     title: '会议室预定一览'
    //   }
    // },
    // {
    //   path: '/loginPage',
    //   name: 'LoginPage',
    //   component: () => import('@/views/LoginPage'),
    //   meta: {
    //     requireAuth: false,
    //     title: '请登录'
    //   }
    // },
    // {
    //   path: '/regularContest',
    //   name: 'RegularContest',
    //   component: () => import('@/views/RegularContest'),
    //   meta: {
    //     requireAuth: true,
    //     title: '正则表达式之战竞赛'
    //   }
    // },
    // {
    //   path: '/regularContestScene',
    //   name: 'RegularContestScene',
    //   component: () => import('@/views/RegularContestScene'),
    //   meta: {
    //     requireAuth: true,
    //     title: '正则表达式之战'
    //   }
    // },
    {
      path: '/vpn',
      name: 'VPN使用统计',
      component: () => import('@/views/Vpn'),
      meta: {
        requireAuth: false,
        title: 'VPN使用统计'
      }
    },
    // {
    //   path: '/contest',
    //   name: 'Contest',
    //   component: () => import('@/views/Contest'),
    //   meta: {
    //     requireAuth: true,
    //     title: '竞赛答题区'
    //   }
    // },
    // {
    //   path: '/contestAdmin',
    //   name: 'ContestAdmin',
    //   component: () => import('@/views/ContestAdmin'),
    //   meta: {
    //     requireAuth: true,
    //     title: '竞赛评分区'
    //   }
    // },
    // {
    //   path: '/contestScene',
    //   name: 'ContestScene',
    //   component: () => import('@/views/ContestScene'),
    //   meta: {
    //     requireAuth: true,
    //     title: '排行榜'
    //   }
    // },
    // {
    //   path: '/messageBoard',
    //   name: 'MessageBoard',
    //   component: () => import('@/views/MessageBoard'),
    //   meta: {
    //     requireAuth: true,
    //     title: '留言板'
    //   }
    // },
    {
      path: '/*',
      redirect: '/vpn'
    }
  ]
})
