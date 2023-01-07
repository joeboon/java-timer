# Experiments to check behaviour of Java Timer class

## Chat with Baeldung

[Eugen (Baeldung) @baeldung Jan 6, 2023](https://twitter.com/baeldung/status/1611376846335246338)  
Replying to @Joeboon  
Thanks Joe, looking into it

Eugen (Baeldung) liked your Tweet  
[Joe Boon (@Joeboon) Jan 5, 2023](https://twitter.com/Joeboon/status/1611116050707349505)  
@baeldung Hi, I think the article on Timer has some errors, and there might be some improvements that could be made. https://baeldung.com/java-timer-and-timertask - Section 3 timing charts look wrong. The .schedule example with timings could be improved with a delay in the TimerTask. Happy to chat.


## Timing charts

Extract from https://www.baeldung.com/java-timer-and-timertask#repeat

<blockquote>
<h2 id="bd-repeat" data-id="repeat">3. Schedule a Repeatable a Task</h2>
<div class="bd-anchor" id="repeat"></div>
<p>Now that we've covered how to schedule the single execution of a task, let's see how to deal with repeatable tasks.</p>
<p>Once again, the <em>Timer</em> class offers multiple possibilities. We can set up the repetition to observe either a fixed delay or a fixed rate.</p>
<p><strong>A fixed delay means that the execution will start a period of time after the moment the last execution started, even if it was delayed (therefore being itself delayed)</strong>.</p><div class='code-block code-block-4' style='margin: 8px 0; clear: both;'>
<div align="center" data-freestar-ad="__300x250 __336x280" id="baeldung_leaderboard_mid_3">
<script data-cfasync="false" type="text/javascript">
    freestar.config.enabled_slots.push({ placementName: "baeldung_leaderboard_mid_3", slotId: "baeldung_leaderboard_mid_3" });
  </script>
</div></div>
<p>Let's say we want to schedule a task every two seconds, with the first execution taking one second and the second one taking two, but being delayed by one second. Then the third execution starts at the fifth second:</p>
<pre><code class="language-plaintext">0s     1s    2s     3s           5s
|--T1--|
|-----2s-----|--1s--|-----T2-----|
|-----2s-----|--1s--|-----2s-----|--T3--|</code></pre>
<p>On the other hand, <strong>a fixed rate means that each execution will respect the initial schedule, no matter if a previous execution has been delayed</strong>.</p>
<p>Let's reuse our previous example. With a fixed rate, the second task will start after three seconds (because of the delay), but the third one will start after four seconds (respecting the initial schedule of one execution every two seconds):</p>
<pre><code class="language-plaintext">0s     1s    2s     3s    4s
|--T1--|       
|-----2s-----|--1s--|-----T2-----|
|-----2s-----|-----2s-----|--T3--|</code></pre>
</blockquote>


## Schedule examples

<blockquote>
<h3 id="bd-repeat-fixed-delay" data-id="repeat-fixed-delay">3.1. With a Fixed Delay</h3>
<div class="bd-anchor" id="repeat-fixed-delay"></div>
<p>Now let's imagine we want to implement a newsletter system, sending an email to our followers every week. In this case, a repetitive task seems ideal.</p>
<p>So let's schedule the newsletter every second, which is basically spamming, but as the sending is fake, we're good to go.</p>
<p>First, we'll design a <em>NewsletterTask</em>:</p>
<pre><code class="language-java">public class NewsletterTask extends TimerTask {
    @Override
    public void run() {
        System.out.println(&quot;Email sent at: &quot; 
          + LocalDateTime.ofInstant(Instant.ofEpochMilli(scheduledExecutionTime()), 
          ZoneId.systemDefault()));
    }
}</code></pre>
<p>Each time it executes, the task will print its scheduled time, which we gather using the <em>TimerTask#scheduledExecutionTime()</em> method.</p>
<p>So what if we want to schedule this task every second in fixed-delay mode? We'll have to use the overloaded version of <em>schedule()</em> that we mentioned earlier:</p>
<pre><code class="language-java">new Timer().schedule(new NewsletterTask(), 0, 1000);

for (int i = 0; i &lt; 3; i++) {
    Thread.sleep(1000);
}</code></pre>
<p>Of course, we only carry the tests for a few occurrences:</p>
<pre><code class="language-plaintext">Email sent at: 2020-01-01T10:50:30.860
Email sent at: 2020-01-01T10:50:31.860
Email sent at: 2020-01-01T10:50:32.861
Email sent at: 2020-01-01T10:50:33.861</code></pre>
<p>As we can see, there's at least one second between each execution, but they're sometimes delayed by a millisecond. <strong>This phenomenon is due to our decision to used fixed-delay repetition.</strong></p>
<h3 id="bd-repeat-fixed-rate" data-id="repeat-fixed-rate">3.2. With a Fixed Rate</h3>
<div class="bd-anchor" id="repeat-fixed-rate"></div>
<p>Now what if we were to use a fixed-rate repetition? Then we would have to use the <em>scheduledAtFixedRate()</em> method:</p><div class='code-block code-block-6' style='margin: 8px 0; clear: both;'>
<div align="center" data-freestar-ad="__336x280 __336x280" id="baeldung_incontent_2">
<script data-cfasync="false" type="text/javascript">
    freestar.config.enabled_slots.push({ placementName: "baeldung_incontent_2", slotId: "baeldung_incontent_2" });
  </script>
</div></div>
<pre><code class="language-java">new Timer().scheduleAtFixedRate(new NewsletterTask(), 0, 1000);

for (int i = 0; i &lt; 3; i++) {
    Thread.sleep(1000);
}</code></pre>
<p>This time, <strong>executions aren't delayed by the previous ones</strong>:</p>
<pre><code class="language-plaintext">Email sent at: 2020-01-01T10:55:03.805
Email sent at: 2020-01-01T10:55:04.805
Email sent at: 2020-01-01T10:55:05.805
Email sent at: 2020-01-01T10:55:06.805</code></pre>
</blockquote>

