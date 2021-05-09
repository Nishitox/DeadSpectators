# DeadSpectators
死亡したプレイヤーをスペクテーターに設定するプラグインです。

## How to use
`/deadspectators [enable|disable|config]` で有効/無効、及び設定の変更をすることができます。

第二引数を指定せずにconfigのみで実行すると、configの状態をチャット覧に表示します。  
`/deadspectators config`

### configの詳細：
| 項目             | 説明                                         | 初期値                | 型      |
| ---------------- | -------------------------------------------- | -------------------- | ------- |
| deadSpectators   | プラグインの有効・無効を設定します              | false                | boolean |
| deathTitle       | 死亡時に表示するタイトルを設定します            | "ＹＯＵ ＤＩＥＤ ！"   | String  |
| skipDeathMenu    | ゲームオーバーメニューをスキップするか設定します | false                | boolean |
| updateSpawnpoint | スポーン地点を死亡地点に変更するか設定します     | false                | boolean |
