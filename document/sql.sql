SELECT * FROM T_TODAY_DATA;
SELECT * FROM T_REAL_DATA;
SELECT * FROM T_LIVE_DATA;
SELECT * FROM T_SEVEN_DATA;
SELECT * FROM T_LIVE_DATA2;

DELETE T_TODAY_DATA;
DELETE T_REAL_DATA;
DELETE T_LIVE_DATA;
DELETE T_SEVEN_DATA;
DELETE T_LIVE_DATA2;



-- Create table
create table T_LIVE_DATA2
(
  xh            VARCHAR2(50) not null,
  zwxvalue      VARCHAR2(50),
  zwxcontent    VARCHAR2(100),
  gmvalue       VARCHAR2(50),
  gmcontent     VARCHAR2(100),
  cyvalue       VARCHAR2(50),
  cycontent     VARCHAR2(100),
  xcvalue       VARCHAR2(50),
  xccontent     VARCHAR2(100),
  ydvalue       VARCHAR2(50),
  ydcontent     VARCHAR2(100),
  kqwrksvalue   VARCHAR2(50),
  kqwrkscontent VARCHAR2(100),
  cjsj          DATE
)

-- Add comments to the table 
comment on table T_LIVE_DATA2
  is '生活指数2';
-- Add comments to the columns 
comment on column T_LIVE_DATA2.xh
  is '序号';
comment on column T_LIVE_DATA2.zwxvalue
  is '紫外线指数值';
comment on column T_LIVE_DATA2.zwxcontent
  is '紫外线指数内容';
comment on column T_LIVE_DATA2.gmvalue
  is '感冒指数值';
comment on column T_LIVE_DATA2.gmcontent
  is '感冒指数内容';
comment on column T_LIVE_DATA2.cyvalue
  is '穿衣指数值';
comment on column T_LIVE_DATA2.cycontent
  is '穿衣指数内容';
comment on column T_LIVE_DATA2.xcvalue
  is '洗车指数值';
comment on column T_LIVE_DATA2.xccontent
  is '洗车指数内容';
comment on column T_LIVE_DATA2.ydvalue
  is '运动指数值';
comment on column T_LIVE_DATA2.ydcontent
  is '运动指数内容';
comment on column T_LIVE_DATA2.kqwrksvalue
  is '空气污染扩散指数值';
comment on column T_LIVE_DATA2.kqwrkscontent
  is '空气污染扩散指数内容';
comment on column T_LIVE_DATA2.cjsj
  is '采集时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_LIVE_DATA2
  add constraint PK_T_LIVE_DATA2 primary key (XH)


-- Create table
create table T_LIVE_DATA
(
  xh    VARCHAR2(50) not null,
  lx    VARCHAR2(50),
  zhi   VARCHAR2(50),
  desnr VARCHAR2(100),
  cjsj  DATE
)

-- Add comments to the table 
comment on table T_LIVE_DATA
  is '生活指数';
-- Add comments to the columns 
comment on column T_LIVE_DATA.xh
  is '序号';
comment on column T_LIVE_DATA.lx
  is '类型';
comment on column T_LIVE_DATA.zhi
  is '值';
comment on column T_LIVE_DATA.desnr
  is '描述内容';
comment on column T_LIVE_DATA.cjsj
  is '采集时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_LIVE_DATA
  add constraint PK_T_LIVE_DATA primary key (XH)
  
-- Create table
create table T_REAL_DATA
(
  xh         VARCHAR2(50) not null,
  nameen     VARCHAR2(50),
  cityname   VARCHAR2(50),
  city       VARCHAR2(50),
  temp       VARCHAR2(50),
  tempf      VARCHAR2(50),
  wd         VARCHAR2(50),
  wde        VARCHAR2(50),
  ws         VARCHAR2(50),
  wse        VARCHAR2(50),
  sd         VARCHAR2(50),
  updatetime VARCHAR2(50),
  weather    VARCHAR2(50),
  weathere   VARCHAR2(50),
  aqi        VARCHAR2(50),
  aqi_pm25   VARCHAR2(50),
  cjsj       DATE,
  rq         VARCHAR2(50)
)

-- Add comments to the table 
comment on table T_REAL_DATA
  is '当天的实时天气数据';
-- Add comments to the columns 
comment on column T_REAL_DATA.xh
  is '序号';
comment on column T_REAL_DATA.nameen
  is 'NAMEEN';
comment on column T_REAL_DATA.cityname
  is '城市名';
comment on column T_REAL_DATA.city
  is '城市代码';
comment on column T_REAL_DATA.temp
  is '温度';
comment on column T_REAL_DATA.tempf
  is 'TEMPF';
comment on column T_REAL_DATA.wd
  is '风向';
comment on column T_REAL_DATA.wde
  is '风向英文名';
comment on column T_REAL_DATA.ws
  is '风力';
comment on column T_REAL_DATA.wse
  is 'WSE';
comment on column T_REAL_DATA.sd
  is '湿度';
comment on column T_REAL_DATA.updatetime
  is '更新时间';
comment on column T_REAL_DATA.weather
  is '天气';
comment on column T_REAL_DATA.weathere
  is 'WEATHERE';
comment on column T_REAL_DATA.aqi
  is 'AQI';
comment on column T_REAL_DATA.aqi_pm25
  is 'AQI_PM25';
comment on column T_REAL_DATA.cjsj
  is '采集时间';
comment on column T_REAL_DATA.rq
  is '日期';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_REAL_DATA
  add constraint PK_T_REAL_DATA primary key (XH)
  
-- Create table
create table T_SEVEN_DATA
(
  xh               VARCHAR2(50) not null,
  wea              VARCHAR2(50),
  daytimetem       VARCHAR2(50),
  nighttem         VARCHAR2(50),
  daytimewin       VARCHAR2(50),
  nightwin         VARCHAR2(50),
  winpower         VARCHAR2(50),
  cjsj             DATE,
  rq               DATE,
  rqdes            VARCHAR2(50),
  daytimewea       VARCHAR2(50),
  nightwea         VARCHAR2(50),
  daytimewindpower VARCHAR2(50),
  nightwindpower   VARCHAR2(50)
)

-- Add comments to the table 
comment on table T_SEVEN_DATA
  is '7天天气数据';
-- Add comments to the columns 
comment on column T_SEVEN_DATA.xh
  is '序号';
comment on column T_SEVEN_DATA.wea
  is 'WEA';
comment on column T_SEVEN_DATA.daytimetem
  is 'DAYTIMETEM';
comment on column T_SEVEN_DATA.nighttem
  is 'NIGHTTEM';
comment on column T_SEVEN_DATA.daytimewin
  is 'DAYTIMEWIN';
comment on column T_SEVEN_DATA.nightwin
  is 'NIGHTWIN';
comment on column T_SEVEN_DATA.winpower
  is 'WINPOWER';
comment on column T_SEVEN_DATA.cjsj
  is '采集时间';
comment on column T_SEVEN_DATA.rq
  is '日期';
comment on column T_SEVEN_DATA.rqdes
  is '日期描述';
comment on column T_SEVEN_DATA.daytimewea
  is '白天天气';
comment on column T_SEVEN_DATA.nightwea
  is '晚上天气';
comment on column T_SEVEN_DATA.daytimewindpower
  is '白天风力';
comment on column T_SEVEN_DATA.nightwindpower
  is '晚上风力';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_SEVEN_DATA
  add constraint PK_T_SEVEN_DATA primary key (XH)
  
-- Create table
create table T_TODAY_DATA
(
  xh                   VARCHAR2(50) not null,
  daytimetem           VARCHAR2(50),
  daytimeweather       VARCHAR2(50),
  daytimewinddirection VARCHAR2(50),
  daytimewindlevel     VARCHAR2(50),
  nighttem             VARCHAR2(50),
  nightweather         VARCHAR2(50),
  nightwinddirection   VARCHAR2(50),
  nightwindlevel       VARCHAR2(50),
  cjsj                 DATE
)

-- Add comments to the table 
comment on table T_TODAY_DATA
  is '当天的天气数据';
-- Add comments to the columns 
comment on column T_TODAY_DATA.xh
  is '序号';
comment on column T_TODAY_DATA.daytimetem
  is '白天最高温度';
comment on column T_TODAY_DATA.daytimeweather
  is '白天天气情况';
comment on column T_TODAY_DATA.daytimewinddirection
  is '白天风向';
comment on column T_TODAY_DATA.daytimewindlevel
  is '白天风力级别';
comment on column T_TODAY_DATA.nighttem
  is '夜间最低温度';
comment on column T_TODAY_DATA.nightweather
  is '夜间天气情况';
comment on column T_TODAY_DATA.nightwinddirection
  is '晚上风向';
comment on column T_TODAY_DATA.nightwindlevel
  is '夜间风力级别';
comment on column T_TODAY_DATA.cjsj
  is '采集时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_TODAY_DATA
  add constraint PK_T_TODAY_DATA primary key (XH)
  
  
  
  
  
  

