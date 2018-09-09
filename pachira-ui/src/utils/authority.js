import jwtDecode from 'jwt-decode';
// use localStorage to store the authority info, which might be sent from server in actual project.
export function getAuthority() {
  // return localStorage.getItem('antd-pro-authority') || ['admin', 'user'];
  //return localStorage.getItem('antd-pro-authority') || 'admin';
  return localStorage.getItem('antd-pro-authority');
}

export function setAuthority(authority) {
  if(authority.success){
    let result = authority.result;
    let jwt = JSON.parse(result);
    let decoded = jwtDecode(jwt.access_token);
    //console.log(decoded);
    localStorage.setItem('access_token', jwt.access_token);
    return localStorage.setItem('antd-pro-authority', decoded.authorities[0]);
  }else{
    localStorage.setItem('access_token', '');
    return localStorage.setItem('antd-pro-authority', '');
  }
}
