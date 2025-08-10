# Hex Spring Modular Example

這是一個基於 **Spring Boot** 的六邏輯層（Hexagonal Architecture）範例專案，使用 **JPA** 與 **H2 In-Memory Database**，實作了簡單的 `Order` CRUD 功能，並模組化拆分為多個 Maven module。

## 專案結構

```
hex-spring-modular/
├── domain                 # 核心領域邏輯與實體
├── application            # 用例與服務層
├── infrastructure-jpa     # JPA 資料庫實作
├── web                    # REST API 入口
└── pom.xml                # 父 POM
```

## 系統功能

- 建立訂單（POST /api/orders）
- 查詢全部訂單（GET /api/orders）
- 依 ID 查詢訂單（GET /api/orders/{id}）
- H2 Memory DB 儲存（啟動時自動建立表格並插入測試資料）

## 建置與啟動

```bash
# 1. 編譯與打包所有模組
mvn clean install

# 2. 啟動 Web 模組
mvn -pl web spring-boot:run
```

啟動後，Tomcat 會在 **<http://localhost:8080/>** 運行。

---

## 測試 API

啟動後可以用以下方式測試：

### 1. 瀏覽器查看

```
http://localhost:8080/api/orders
```

**範例回應：**

```json
[
  {
    "id": 1,
    "customerName": "Alice",
    "totalAmount": 100.0
  },
  {
    "id": 2,
    "customerName": "Bob",
    "totalAmount": 50.5
  }
]
```

### 2. 取得全部訂單

```powershell
curl http://localhost:8080/api/orders
```

### 3. 新增一筆訂單

```powershell
curl -X POST http://localhost:8080/api/orders `
     -H "Content-Type: application/json" `
     -d '{"customerName":"Charlie","totalAmount":12.34}'
```

**範例回應：**

```json
{
  "id": 3,
  "customerName": "Charlie",
  "totalAmount": 12.34
}
```

### 4. 查詢單筆訂單

（將 `{id}` 換成新增後回傳的 id）

```powershell
curl http://localhost:8080/api/orders/{id}
```

**範例回應：**

```json
{
  "id": 1,
  "customerName": "Alice",
  "totalAmount": 100.0
}
```

---

## 使用 H2 Console 查看資料

1. 開啟：

```
http://localhost:8080/h2-console
```

2. 設定連線資訊：

```
JDBC URL: jdbc:h2:mem:testdb
User: sa
Password: (留空)
```

3. 點擊 **Connect** 後即可查看 `orders` 資料表內容。

---

## 預設測試資料

系統啟動時會自動插入兩筆測試資料：

| id  | customer_name | total_amount |
|-----|--------------|--------------|
| 1   | Alice        | 100.00       |
| 2   | Bob          | 50.50        |

---

## 開發環境需求

- JDK 21+
- Maven 3.9+
- Spring Boot 3.x
