/* eslint-disable no-mixed-spaces-and-tabs */
<template>
  <div id="app">
    <div class="page-box">
      <div class="head">VPN账号管理</div>
      <div class="content">
        <div class="content-title">VPN使用规范</div>

        <div class="article">
          <div class="article-text">能通过远程指导值班人员完成的日常事务不建议使用VPN，例如查询任务单、传递材料、审批版本、功能点、工时分配等。</div>
          <div class="article-text">其它人员如需使用可以向具体IP负责人申请，说明使用原因及具体时间，达成一致后再进行使用。</div>
          <div class="article-text">如其它人出现紧急事务需要处理，IP负责人也在生产任务处理中，可以由团队层面进行沟通解决，必要时上升部门管理人员。</div>
          <div class="article-text text-bold">与负责人沟通后可预订，使用完毕后请将状态修改为“可预订”，及时释放。</div>
        </div>

        <div class="content-title">VPN使用申请</div>

        <div>
          <x-table full-bordered style="background-color:#fff;">
            <thead>
              <tr style="background-color: #F7F7F7">
                <th class="date">日期</th>
                <th class="time">时间段</th>
                <th class="ip">{{ipPre+61}}<br/>侯猛</th>
                <th class="ip">{{ipPre+62}}<br/>韩健</th>
                <th class="ip">{{ipPre+63}}<br/>刘宁</th>
                <th class="ip">{{ipPre+64}}<br/>崔海春</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td rowspan="4">{{todayDate}}</td>
                <td>8:00-12:00</td>
                <td
                  v-for="(item,index) in vpnStatus[0]"
                  :key="index"
                  @click="clickTable(0,index,$event)"
                >
                  <template v-if="cellChangeToInput[0][index]"><p class="cell-text">{{item}}</p></template>
                  <template v-else>
                    <input type="text" v-model="newName" />
                    <input type="button" value="提交" @click.stop="processSubmit(0,index)" />
                    <input type="button" value="取消" @click.stop="processCancel(0,index)" />
                  </template>
                </td>
              </tr>

              <tr>
                <td>12:00-16:00</td>
                <td
                  v-for="(item,index) in vpnStatus[1]"
                  :key="index"
                  @click="clickTable(1,index,$event)"
                >
                  <template v-if="cellChangeToInput[1][index]"><p class="cell-text">{{item}}</p></template>
                  <template v-else>
                    <input type="text" v-model="newName" />
                    <input type="button" value="提交" @click.stop="processSubmit(1,index)" />
                    <input type="button" value="取消" @click.stop="processCancel(1,index)" />
                  </template>
                </td>
              </tr>

              <tr>
                <td>16:00-20:00</td>
                <td
                  v-for="(item,index) in vpnStatus[2]"
                  :key="index"
                  @click="clickTable(2,index,$event)"
                >
                  <template v-if="cellChangeToInput[2][index]"><p class="cell-text">{{item}}</p></template>
                  <template v-else>
                    <input type="text" v-model="newName" />
                    <input type="button" value="提交" @click.stop="processSubmit(2,index)" />
                    <input type="button" value="取消" @click.stop="processCancel(2,index)" />
                  </template>
                </td>
              </tr>

              <tr>
                <td>20:00-24:00</td>
                <td
                  v-for="(item,index) in vpnStatus[3]"
                  :key="index"
                  @click="clickTable(3,index,$event)"
                >
                  <template v-if="cellChangeToInput[3][index]"><p class="cell-text">{{item}}</p></template>
                  <template v-else>
                    <input type="text" v-model="newName" />
                    <input type="button" value="提交" @click.stop="processSubmit(3,index)" />
                    <input type="button" value="取消" @click.stop="processCancel(3,index)" />
                  </template>
                </td>
              </tr>

              <tr>
                <td rowspan="4">{{tomorrowDate}}</td>
                <td>8:00-12:00</td>
                <td
                  v-for="(item,index) in vpnStatus[4]"
                  :key="index"
                  @click="clickTable(4,index,$event)"
                >
                  <template v-if="cellChangeToInput[4][index]"><p class="cell-text">{{item}}</p></template>
                  <template v-else>
                    <input type="text" v-model="newName" />
                    <input type="button" value="提交" @click.stop="processSubmit(4,index)" />
                    <input type="button" value="取消" @click.stop="processCancel(4,index)" />
                  </template>
                </td>
              </tr>

              <tr>
                <td>12:00-16:00</td>
                <td
                  v-for="(item,index) in vpnStatus[5]"
                  :key="index"
                  @click="clickTable(5,index,$event)"
                >
                  <template v-if="cellChangeToInput[5][index]"><p class="cell-text">{{item}}</p></template>
                  <template v-else>
                    <input type="text" v-model="newName" />
                    <input type="button" value="提交" @click.stop="processSubmit(5,index)" />
                    <input type="button" value="取消" @click.stop="processCancel(5,index)" />
                  </template>
                </td>
              </tr>
              <tr>
                <td>16:00-20:00</td>
                <td
                  v-for="(item,index) in vpnStatus[6]"
                  :key="index"
                  @click="clickTable(6,index,$event)"
                >
                  <template v-if="cellChangeToInput[6][index]"><p class="cell-text">{{item}}</p></template>
                  <template v-else>
                    <input type="text" v-model="newName" />
                    <input type="button" value="提交" @click.stop="processSubmit(6,index)" />
                    <input type="button" value="取消" @click.stop="processCancel(6,index)" />
                  </template>
                </td>
              </tr>
              <tr>
                <td>20:00-24:00</td>
                <td
                  v-for="(item,index) in vpnStatus[7]"
                  :key="index"
                  @click="clickTable(7,index,$event)"
                >
                  <template v-if="cellChangeToInput[7][index]"><p class="cell-text">{{item}}</p></template>
                  <template v-else>
                    <input type="text" v-model="newName" />
                    <input type="button" value="提交" @click.stop="processSubmit(7,index)" />
                    <input type="button" value="取消" @click.stop="processCancel(7,index)" />
                  </template>
                </td>
              </tr>
            </tbody>
          </x-table>
        </div>
      </div>
      <div class="foot">
        <span>© boysama</span>
      </div>
      <toast v-model="toastValue" :type="toastType" :time="1400" :text="toastText"></toast>
    </div>
  </div>
</template>
<script>
import { XTable, Toast, dateFormat } from "vux";
import { getVpnList, submitVpn } from "@/api/vpn.js";

const vpnStatusDefault = [
  ["可预订", "可预订", "可预订", "可预订"],
  ["可预订", "可预订", "可预订", "可预订"],
  ["可预订", "可预订", "可预订", "可预订"],
  ["可预订", "可预订", "可预订", "可预订"],
  ["可预订", "可预订", "可预订", "可预订"],
  ["可预订", "可预订", "可预订", "可预订"],
  ["可预订", "可预订", "可预订", "可预订"],
  ["可预订", "可预订", "可预订", "可预订"]
];

var cellChangeToInputDefault = [
  [true, true, true, true],
  [true, true, true, true],
  [true, true, true, true],
  [true, true, true, true],
  [true, true, true, true],
  [true, true, true, true],
  [true, true, true, true],
  [true, true, true, true]
];

export default {
  components: {
    XTable,
    Toast
  },
  created() {

      //html字体大小
    //   if(document.documentElement.clientWidth>1000) {
    //       document.st
    //   }
    //获取表格中的今天和明天的日期
    var dateTmp = new Date();
    var nowMonth = dateTmp.getMonth() + 1;
    var nowDay = dateTmp.getDate();
    this.todayDate = nowMonth + "月" + nowDay + "日";

    var tomorrowDateTmp = new Date();
    tomorrowDateTmp.setDate(tomorrowDateTmp.getDate() + 1);
    this.tomorrowDate =
      tomorrowDateTmp.getMonth() + 1 + "月" + tomorrowDateTmp.getDate() + "日";

    //格式化后去获取列表
    this.reqToday = dateFormat(dateTmp, "MMDD");
    this.reqTomorrow = dateFormat(tomorrowDateTmp, "MMDD");
    this.getAllVpnList({ date: this.reqToday });
    this.getAllVpnList({ date: this.reqTomorrow });

    // getAllVpnList()

    //getdata
    var vpnStatusData = [
      {
        id: "1",
        date: "20200205",
        time: "0800-1200",
        ip: "10.100.15.61",
        text: "瞿欢"
      },
      {
        id: "2",
        date: "20200205",
        time: "1200-1600",
        ip: "10.100.15.61",
        text: "瞿欢2"
      }
    ];
    //
    //   vpnStatusTmp
  },
  computed: {
  	  	ipPre: function(){
  	  		return document.documentElement.clientWidth>520?"10.100.15.":"*."
  	  	}
  	  },
  methods: {
    clickTable(index1, index2, e) {
      //   console.log(e.target.cellIndex)
      if (!this.canEdit) {
        return false;
      }
      //   this.cellChangeToInput[index1][index2] = false;
      this.$set(this.cellChangeToInput[index1], index2, false);
      this.canEdit = false;
    },
    processSubmit(index1, index2) {
      console.log(index1, index2);
      //   this.vpnStatus[index1][index2] = this.newName;
      if (this.newName === "") {
        this.processCancel(index1, index2);
        return false;
      }
      //post data
      var reqVpn = {
        date: index1 < 4 ? this.reqToday : this.reqTomorrow,
        time: this.timeComputed(index1),
        reserveInfo: this.newName,
        ip: this.ipComputed(index2)
      };
      submitVpn(reqVpn)
        .then(data => {
          if (data.data && data.data.success) {
            this.toastType = "text";
            this.toastValue = true;
            this.toastText = "预定成功~";
          } else {
            this.toastType = "text";
            this.toastValue = true;
            this.toastText = data.data.data.msg;
          }
        })
        .catch(err => {
          this.toastType = "cancel";
          this.toastValue = true;
          this.toastText = "预定异常，请刷新页面再试";
        });
      this.$set(this.vpnStatus[index1], index2, this.newName);
      this.newName = "";
      this.canEdit = true;
      //   this.cellChangeToInput[index1][index2] = true;
      this.$set(this.cellChangeToInput[index1], index2, true);
      this.getAllVpnList({ date: this.reqToday });
    this.getAllVpnList({ date: this.reqTomorrow });
    },
    processCancel(index1, index2) {
      //changeTo old
      this.newName = "";
      this.canEdit = true;
      //   this.cellChangeToInput[index1][index2] = true;
      this.$set(this.cellChangeToInput[index1], index2, true);
    },
    getAllVpnList(date) {
      getVpnList(date)
        .then(data => {
          if (data.data && data.data.success) {
            if (data.data.data.list.length > 0) {
              this.renderData(data.data.data.list);
            }
            return true;
          } else {
            this.toastType = "text";
            this.toastValue = true;
            this.toastText = data.data.data.msg;
          }
        })
        .catch(err => {
          this.toastType = "cancel";
          this.toastValue = true;
          this.toastText = "查询异常，请刷新页面再试";
        });
    },
    timeComputed(index) {
      var time = "";
      switch (index) {
        case 0:
        case 4:
          time = "0800-1200";
          break;
        case 1:
        case 5:
          time = "1200-1600";
          break;
        case 2:
        case 6:
          time = "1600-2000";
          break;
        case 3:
        case 7:
          time = "2000-2400";
          break;
        default:
          time = "";
      }
      return time;
    },
    ipComputed(index) {
      var time = "";
      switch (index) {
        case 0:
          time = "10.100.15.61";
          break;
        case 1:
          time = "10.100.15.62";
          break;
        case 2:
          time = "10.100.15.63";
          break;
        case 3:
          time = "10.100.15.64";
          break;
        default:
          time = "";
      }
      return time;
    },
    renderData(data) {
      data.forEach(element => {
        var index1Date = 99;
        var index1Time = 99;
        var index2 = 99;
        switch (element.ip) {
          case "10.100.15.61":
            index2 = 0;
            break;
          case "10.100.15.62":
            index2 = 1;
            break;
          case "10.100.15.63":
            index2 = 2;
            break;
          case "10.100.15.64":
            index2 = 3;
            break;
          default:
            index2 = 99;
        }
        switch (element.time) {
          case "0800-1200":
            index1Time = 0;
            break;
          case "1200-1600":
            index1Time = 1;
            break;
          case "1600-2000":
            index1Time = 2;
            break;
          case "2000-2400":
            index1Time = 3;
            break;
          default:
            index1Time = 99;
        }
        if (element.date === this.reqToday) {
          index1Date = 0;
        } else if (element.date === this.reqTomorrow) {
          index1Date = 1;
        } else {
          index1Date = 99;
        }
        if (index2 != 99 && index1Date != 99 && index1Time != 99) {
          var index1 = index1Time + index1Date * 4;
          this.$set(this.vpnStatus[index1], index2, element.reserveInfo);
        }
      });
    }
  },
  data() {
    return {
      vpnStatus: vpnStatusDefault,
      cellChangeToInput: cellChangeToInputDefault,
      canEdit: true,
      newName: "",
      todayDate: "",
      tomorrowDate: "",
      reqToday: "",
      reqTomorrow: "",
      toastText: "",
      toastValue: false,
      toastType: "cancel"
    };
  }
};
</script>
<style scoped>
.page-box {
  display: flex;
  flex-direction: column;
}
.head {
  height: 10vh;
  font-size: 1.875rem;
  line-height: 10vh;
  font-weight: bold;
}
.content {
  display: flex;
  flex-direction: column;
  padding: 10px;
}
.content-title {
  text-align: left;
  font-size: 1.25rem;
  font-weight: bold;
  margin-bottom: 1.25rem;
}
.article {
  margin-bottom: 2rem;
  font-size: 1rem;
}
.title-div {
  font-size: 1.25rem;
  line-height: 1.875rem;
}
.sub-title-div {
  line-height: 1.25rem;
}
.article-img {
  max-width: 100%;
  /* max-height: 200px; */
  margin-top: 1.25rem;
}
.article-text {
  width: 100%;
  text-indent: 2em;
  line-height: 2;
  text-align: left;
}
.text-bold {
    font-weight: bold;
}
.foot {
  text-align: center;
  color: #8c8c8c;
  text-decoration: none;
  outline: none;
  font-size: 0.75rem;
  line-height: 3.125rem;
  height: 3.125rem;
}
.foot a {
  cursor: pointer;
}
.ip {
  /* min-width: 10rem; */
  line-height: 1.4;
  padding: 10px 2px;
}
.date {
  min-width: 3.75rem;
}
.time {
  min-width: 5.5rem;
}
input[type="text"] {
  width: 5em;
}
.button-submit {
  margin-left: 5px;
  width: 4em;
}
.cell-text {
    text-decoration: underline;
    margin: 0;
    cursor: pointer;
}
</style>
