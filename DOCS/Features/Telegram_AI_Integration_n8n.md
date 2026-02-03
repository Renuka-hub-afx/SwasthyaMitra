# Telegram + n8n AI Integration Plan 🤖🛰️

This plan replaces the WhatsApp strategy with a **Telegram Bot** powered by **n8n**. This setup is more flexible, easier to debug, and allows for complex logic without writing custom server code.

---

## 🛠️ 1. The Architecture (Low-Code Power)

| Component | Technology | Role |
| :--- | :--- | :--- |
| **User Interface** | **Telegram Bot** | Where users receive plans and ask questions. |
| **Logic Engine** | **n8n** | Connects all APIs, handles triggers, and formats data. |
| **Brain** | **Gemini 2.0 Flash** | Generates responses and health advice. |
| **Memory** | **Firebase Firestore** | Stores user logs, goals, and Telegram ChatIDs. |

---

## 🏗️ 2. Workflow 1: The AI Health Coach (Chat)
*Trigger: When a user sends a message to the Telegram Bot.*

### n8n Logic Steps:
1.  **Telegram Trigger**: Capture the message and `chatId`.
2.  **Firestore Query**: Find the user profile using their mapped `telegramUsername` or `phone`.
3.  **AI node (Gemini)**: 
    - **Prompt**: "You are SwasthyaMitra AI. User Name: {{name}}. Goal: {{goal}}. Message: {{message}}. Respond concisely."
4.  **Telegram Send Message**: Send the AI response back to the `chatId`.

---

## 📢 3. Workflow 2: Daily Proactive Digest
*Trigger: Daily Cron (8:00 AM).*

### n8n Logic Steps:
1.  **Schedule Trigger**: Runs every morning.
2.  **Firestore Read**: Fetch all users who have opted into Telegram notifications.
3.  **Firestore Read (Daily Plan)**: For each user, fetch their `ai_generated_plans` entry for today.
4.  **Formatting Node**: Combine Meal Plan + Exercise into a beautiful Telegram message.
5.  **Telegram Send Message**: Broadcast to each user's `chatId`.

---

## 🚀 4. Step-by-Step Build Guide

### Step 1: Create the Bot
1.  Message **@BotFather** on Telegram.
2.  Create `/newbot` and get your **API Token**.

### Step 2: Setup n8n
1.  Install n8n (Host on VPS, Docker, or Railway.app).
2.  Add **Credentials**:
    - **Telegram Bot API**: Using your token.
    - **Google AI**: Using your Gemini API Key.
    - **Firebase**: Using your `serviceAccountKey.json`.

### Step 3: Link App to Telegram
To send proactive messages, n8n needs the user's `chatId`.
1.  Add a "Link Telegram" button in your Android App.
2.  The button opens: `https://t.me/YourBotName?start=USER_ID`.
3.  When the user clicks "Start" on Telegram, n8n captures the `USER_ID` and saves the `chatId` back to Firestore.

---

## ⚠️ Why n8n instead of Meta/WhatsApp?
1.  **No Templates Needed**: Unlike WhatsApp, Telegram lets you send any text/images without pre-approval.
2.  **Rich Media**: Easy to send workout GIFs or meal photos.
3.  **Debugging**: You can see exactly where the data fails in the n8n UI.

> [!IMPORTANT]
> To use n8n with Telegram, you must ensure n8n is "publicly accessible" (use a VPS or a tunnel like **ngrok** for local testing).
