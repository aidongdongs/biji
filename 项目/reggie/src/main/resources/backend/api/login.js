//登录接口的ajax
function loginApi(data) {
  console.log(data)
  return $axios({
    'url': '/employee/login',
    'method': 'post',
    data
  })
}
//推出登录的ajax
function logoutApi(){
  return $axios({
    'url': '/employee/logout',
    'method': 'post',
  })
}
